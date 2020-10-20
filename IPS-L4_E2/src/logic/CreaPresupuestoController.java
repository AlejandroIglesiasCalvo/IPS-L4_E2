package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import dataBase.DataBase;
import logic.dto.Presupuesto;
import logic.dto.Producto;

public class CreaPresupuestoController {

	DataBase db;

	private double total = 0.0;

	private List<Producto> productosEnPresupuesto;

	private List<Producto> catalogo;

	private String id;

	private Presupuesto presupuesto;

	private Set<String> tipos = new HashSet<>();
	private String[] tiposComoBox = new String[tipos.size()];

	/**
	 * constructor de la clase, genera un id para el presupuesto que estamos
	 * haciendo y carga el catalogo
	 */
	public CreaPresupuestoController() {
		productosEnPresupuesto = new ArrayList<>();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catalogo = db.getGestionCreaPresupuesto().getProductos();
		generateId();
		addTipos();
	}

	private void addTipos() {
		tipos.add("Sin definir");
		for (Producto p : catalogo) {
			tipos.add(p.getTipo());
		}

	}

	public String[] getTipos() {
		return tipos.toArray(tiposComoBox);
	}

	/**
	 * metodo que genera el id del presupuesto
	 */
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

	/**
	 * metodo que se encarga de hacer la eliminacion de un producto del presupuesto
	 * lo quita de la lista de productos de este y quita su precio del total
	 * 
	 * @param producto
	 * @return
	 */
	public String updateTotalEliminarProduct(Producto producto) {
		productosEnPresupuesto.remove(producto);
		total -= producto.getPrecio();
		return Double.toString(total);
	}

	/**
	 * metodo que se encarga de hacer la addicion de un producto al presupuesto lo
	 * a�ade a la lista de productos de este y suma suprecio al total
	 * 
	 * @param producto
	 * @return
	 */
	public String updateTotalAddProduct(Producto producto) {
		productosEnPresupuesto.add(producto);
		total += producto.getPrecio();
		return Double.toString(total);
	}

	/**
	 * primero miramos si el id del presupuesto ya esta utilizado, para que se
	 * cumpla la restriccion luego, luego creamos un dto de nuestro presupuesto para
	 * tenerlo en memoria y al final a�adimos el presupuesto a la base de datos
	 */
	public void crearPresupuesto() {
		while (db.getGestionCreaPresupuesto().checkSiIdYaUtilizado(this.id)) {
			generateId();
		}
		crearPresupuestoDto();
		añadirPresupuestoABase();
	}

	/**
	 * a�adimos el presupuesto a la base y todas sus lineas del carrito
	 * que contienen los productos de este presupuesto.
	 */
	private void añadirPresupuestoABase() {
		db.getGestionCreaPresupuesto().CreaPresupuesto(this.id, this.total);
		for (Producto p : productosEnPresupuesto) {
			db.getGestionCreaPresupuesto().CrearEntradaPresupuesto(p, this.id);
		}
	}

	/**
	 * creamos el presupuesto en memoria
	 */
	private void crearPresupuestoDto() {
		presupuesto = new Presupuesto(this.id, 123, LocalDateTime.now(), this.total, this.productosEnPresupuesto);
	}

	/**
	 * metodo que devuelve el catalogo
	 * 
	 * @return
	 */
	public List<Producto> getProductos() {
		return catalogo;
	}

	/**
	 * metodo que devuelve true cuando tenemos productos en el presupuesto y false
	 * cuando no los tenemos
	 * 
	 * @return
	 */
	public boolean hasProductosEnCompra() {
		return productosEnPresupuesto.size() != 0;
	}

	/**
	 * metodo que filtra la lista como quiere el usuario
	 * 
	 * @param precio
	 * @param tipo
	 * @param maxMin
	 * @return
	 */
	public List<Producto> filtra(double precio, String tipo, String maxMin) {
		List<Producto> productosFiltrados;
		if (!tipo.equals("Sin definir")) {
			if (maxMin.equals("mayor")) {
				productosFiltrados = catalogo.stream().filter(p -> p.getPrecio() > precio && p.getTipo().equals(tipo))
						.collect(Collectors.toList());
			} else {
				productosFiltrados = catalogo.stream().filter(p -> p.getPrecio() <= precio && p.getTipo().equals(tipo))
						.collect(Collectors.toList());
			}
		} else {
			if (maxMin.equals("mayor")) {
				productosFiltrados = catalogo.stream().filter(p -> p.getPrecio() > precio).collect(Collectors.toList());
			} else {
				productosFiltrados = catalogo.stream().filter(p -> p.getPrecio() <= precio)
						.collect(Collectors.toList());
			}
		}

		return productosFiltrados;
	}

	public Presupuesto getPresupueso() {
		return presupuesto;
	}

}
