package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import dataBase.DataBase;
import logic.dto.Pedido;
import logic.dto.Producto;
import logic.dto.ProductoCarrito;
import logic.dto.ProductoPedido;
import logic.dto.Producto_Almacen;

public class CreaPedidosController {
	
	DataBase db;
	
	private double total = 0.0;
	
	private List<ProductoPedido> productosEnPedido;
	
	private List<Producto_Almacen> almacen;
	
	private String id;
	
	private Pedido pedido;
	
	private Set<String> tipos = new HashSet<>();
	private String[] tiposComboBox = new String[tipos.size()];
	
	public CreaPedidosController() {
		productosEnPedido = new ArrayList<>();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		almacen = db.getGestionCreaPresupuesto().getProductosAlmacen();
		
		generateId();
		addTipos();
		
	}
	
	private void addTipos() {
		tipos.add("Sin definir");
		for (Producto_Almacen p : almacen) {
			tipos.add(p.getTipo());
		}

	}
	
	public String[] getTipos() {
		return tipos.toArray(tiposComboBox);
	}

	private void generateId() {
		// van todas seguidas por eso el nombre de las variables
		int leftLimit = 48; // numero 0
		int rightLimit = 122; // letra z

		Random random = new Random();

		// longitud del id
		int targetStringLength = 9;

		id = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	public List<Producto_Almacen> getProductos() {
		return almacen;
	}

	public List<Producto_Almacen> filtra(double precio, String tipo, String maxMin) {
		List<Producto_Almacen> productosFiltrados;
		if (!tipo.equals("Sin definir")) {
			if (maxMin.equals("mayor")) {
				productosFiltrados = almacen.stream().filter(p -> p.getPrecio() > precio && p.getTipo().equals(tipo))
						.collect(Collectors.toList());
			} else {
				productosFiltrados = almacen.stream().filter(p -> p.getPrecio() <= precio && p.getTipo().equals(tipo))
						.collect(Collectors.toList());
			}
		} else {
			if (maxMin.equals("mayor")) {
				productosFiltrados = almacen.stream().filter(p -> p.getPrecio() > precio).collect(Collectors.toList());
			} else {
				productosFiltrados = almacen.stream().filter(p -> p.getPrecio() <= precio)
						.collect(Collectors.toList());
			}
		}

		return productosFiltrados;
	}

	public void crearPedido() {
		while(db.getGestionCreaPedido().checkSiIdYaUtilizado(this.id)) {
			generateId();
		}
		crearPedidoDto();
		añadirPedidoABase();		
	}

	private void añadirPedidoABase() {
		db.getGestionCreaPedido().creaPedido(this.id,total);
		for(ProductoPedido p : productosEnPedido) {
			db.getGestionCreaPedido().crearEntradaPedido(p, this.id);
		}
	}

	private void crearPedidoDto() {
		pedido = new Pedido(this.id,"PENDIENTE",productosEnPedido,total);		
	}

	public String updateTotalAddProduct(Producto_Almacen p) {
		boolean updated = false;
		for (int i = 0; i < productosEnPedido.size(); i++) {
			if (productosEnPedido.get(i).getID().equals(p.getID())) {
				updated = true;
				productosEnPedido.get(i).setUnidades(productosEnPedido.get(i).getUnidades() + 1);
			}
		}
		if (!updated) {
			productosEnPedido.add(new ProductoPedido(p,1));
		}
		total += p.getPrecio();
		return Double.toString(total);
	}

	public List<ProductoPedido> getProductoesEnPedido() {
		return productosEnPedido;
	}

	public String updateTotalEliminarProducto(Producto_Almacen p) {
		for (int i = 0; i < productosEnPedido.size(); i++) {
			if (productosEnPedido.get(i).getID().equals(p.getID())) {
				if (productosEnPedido.get(i).getUnidades() - 1 > 0) {
					productosEnPedido.get(i).setUnidades(productosEnPedido.get(i).getUnidades() - 1);
				} else {
					productosEnPedido.remove(i);
				}
			}
		}
		total -= p.getPrecio();
		return Double.toString(total);
	}

	public boolean hasProductosEnCompra() {
		return productosEnPedido.size() != 0;
	}

	public boolean tieneEsteProducto(Producto_Almacen p) {
		for(int i = 0; i < productosEnPedido.size(); i++) {
			if(productosEnPedido.get(i).getID().equals(p.getID())) {
				return true;
			}
		}
		return false;
	}

}
