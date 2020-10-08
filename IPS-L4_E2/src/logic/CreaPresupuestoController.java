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
	
	/**
	 * constructor de la clase, genera un id para el presupuesto que estamos haciendo
	 * y carga el catalogo
	 */
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

	/**
	 * metodo que genera el id del presupuesto
	 */
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

	/**
	 * metodo que se encarga de hacer la eliminacion de un producto del presupuesto
	 * lo quita de la lista de productos de este y quita su precio del total
	 * @param producto
	 * @return
	 */
	public String updateTotalEliminarProduct(Producto producto) {
		productosEnPresupuesto.remove(producto);
		total -= producto.getPrecio();
		return Double.toString(total);
	}
	
	/**
	 * metodo que se encarga de hacer la addicion de un producto al presupuesto
	 * lo a�ade a la lista de productos de este y suma suprecio al total
	 * @param producto
	 * @return
	 */
	public String updateTotalAddProduct(Producto producto) {
		productosEnPresupuesto.add(producto);
		total += producto.getPrecio();
		return Double.toString(total);
	}
	
	/**
	 * primero miramos si el id del presupuesto ya esta utilizado, para que se cumpla la restriccion
	 * luego, como no tenemos en cuenta las unidades de los productos al a�adirlas, tenemos que mirar
	 * si ya se encuentra en la base de datos una entrada de este presupuesto con el mismo producto
	 * si es as� hacemos un update de las unidades
	 * Si este producto no esta en el presupuesto creamos una nueva entrada en al base de datos
	 */
	public void crearPresupuesto() {
		while(db.getGestionCreaPresupuesto().checkSiIdYaUtilizado(this.id)) {
			generateId();
		}
		db.getGestionCreaPresupuesto().CreaPresupuesto(this.id,this.total);
		for(Producto p : productosEnPresupuesto) {
			if(db.getGestionCreaPresupuesto().checkYaTenemosProducto(p,this.id)) {
				db.getGestionCreaPresupuesto().UpdateUnidadesProducto(p,this.id);
			}else {
				db.getGestionCreaPresupuesto().CrearEntradaPresupuesto(p,this.id);
			}	
		}
		
		
		
	}

	/**
	 * metodo que devuelve el catalogo
	 * @return
	 */
	public List<Producto> getProductos() {
		return catalogo;
	}

	/**
	 * metodo que devuelve true cuando tenemos productos en el presupuesto 
	 * y false cuando no los tenemos
	 * @return
	 */
	public boolean hasProductosEnCompra() {
		return productosEnPresupuesto.size() != 0;
	}	
	
}