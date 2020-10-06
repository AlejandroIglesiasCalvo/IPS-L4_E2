package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dataBase.DataBase;
import logic.dto.Producto;


public class CreaPresupuestoController {
	
	DataBase db;
	
	private double total = 0.0;
	
	private List<Producto> productosEnPresupuesto;
	
	private List<Producto> catalogo;
	
	private String id;
	
	public CreaPresupuestoController(){
		productosEnPresupuesto = new ArrayList();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catalogo = db.getGestionCreaPresupuesto().getProductos();
		generateId();
		
	}

	private void generateId() {
		//van todas seguidas por eso el nombre de las variables
		int leftLimit = 48; // numero 0
		int rightLimit = 122; // letra z
		
		Random random = new Random();
		
		//longitud del id
		int targetStringLength = 9;
		
		id =random.ints(leftLimit, rightLimit + 1)
			      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
			      .limit(targetStringLength)
			      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			      .toString();
	}

	public String updateTotalEliminarProduct(Producto producto) {
		productosEnPresupuesto.remove(producto);
		total -= producto.getPrecio();
		return Double.toString(total);
	}

	public String updateTotalAddProduct(Producto producto) {
		productosEnPresupuesto.add(producto);
		total += producto.getPrecio();
		return Double.toString(total);
	}
	
	/**
	 * primero miramos si el id del presupuesto ya esta utilizado, para que se cumpla la restriccion
	 * luego, como no tenemos en cuenta las unidades de los productos al añadirlas, tenemos que mirar
	 * si ya se encuentra en la base de datos una entrada de este presupuesto con el mismo producto
	 * si es así hacemos un update de las unidades
	 * Si este producto no esta en el presupuesto creamos una nueva entrada en al base de datos
	 */
	public void crearPresupuesto() {
//		while(db.checkSiIdYaUtilizado(this.id)) {
//			generateId();
//		}
//		for(Producto p : productosEnPresupuesto) {
//			if(db.checkYaTenemosProducto(p)) {
//				db.UpdateUnidadesProducto(p);
//			}else {
//				db.CrearEntradaPresupuesto(p);
//			}
//			
//		}
		
		
	}

	public List<Producto> getProductos() {
		return catalogo;
	}

	public boolean hasProductosEnCompra() {
		return productosEnPresupuesto.size() != 0;
	}	
	
}
