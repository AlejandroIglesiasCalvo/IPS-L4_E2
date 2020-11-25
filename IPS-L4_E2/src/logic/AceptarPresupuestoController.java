package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dataBase.DataBase;
import logic.dto.Cliente;
import logic.dto.Pedido;
import logic.dto.Presupuesto;
import logic.dto.Venta;
import logic.dto.ProductoCarrito;
import logic.dto.ProductoPedido;
import logic.dto.Producto_Almacen;
import java.lang.*;

public class AceptarPresupuestoController {

	private DataBase db;
	private Pedido pedido;

	public AceptarPresupuestoController() {

		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Cliente getClienteById(String id) {
		return db.getGestionCliente().getClienteById(id);
	}

	public ArrayList<Presupuesto> getPresupuestosValidos() {

		return db.getGestionCreaPresupuesto().getPresupuestosValidos();
	}
	public String getFechaPresupuesto(Venta v) {

		return db.getGestionCreaPresupuesto().getPresupuestosDeVentaID(v.getID());
	}
	private String generateId() {
		// van todas seguidas por eso el nombre de las variables
		int leftLimit = 48; // numero 0
		int rightLimit = 122; // letra z
		String id;

		Random random = new Random();

		// longitud del id
		int targetStringLength = 9;

		id = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return id;
	}
	
	public Venta crearVenta(Presupuesto p) {
		return db.getGestionVentas().insertarVenta(generateId(),p);
	}

	public boolean checkStockInAlmacen(Presupuesto p) {
		List<ProductoPedido> pedidos = new ArrayList<>();
		List<Producto_Almacen> almacen = db.getGestionCreaPresupuesto().getProductosAlmacen();
		for (Producto_Almacen pa : almacen) {
			for (ProductoCarrito pc : p.getProductos()) {
				if (pa.getID().equals(pc.getID())) {
					if (pa.getStock() - pc.getUnidades() < 0) {
						pedidos.add(new ProductoPedido(pa, Math.abs(pa.getStock() - pc.getUnidades())));
						db.getGestionAlmacen().updateProducto(pa, 0);
					} else {
						db.getGestionAlmacen().updateProducto(pa, pa.getStock() - pc.getUnidades());
					}
				}
			}
		}
		if (pedidos.size() > 0) {
			createPedido(pedidos);
			return false;
		}
		return true;

	}

	private void createPedido(List<ProductoPedido> pedidos) {
		String id = generateId();
		while (db.getGestionAlmacen().checkSiIdYaUtilizado(id)) {
			id = generateId();
		}
		crearPedidoDto(id, pedidos);
		añadirPedidoABase(pedidos);
	}

	private void añadirPedidoABase(List<ProductoPedido> pedidos) {
		db.getGestionAlmacen().creaPedido(pedido);
		for(ProductoPedido pe : pedidos) {
			db.getGestionAlmacen().creaEntredaPedido(pedido.getID(),pe);
		}
	}

	private void crearPedidoDto(String id, List<ProductoPedido> pedidos) {
		this.pedido = new Pedido(id, "SOLICITADO", pedidos);		
	}

}
