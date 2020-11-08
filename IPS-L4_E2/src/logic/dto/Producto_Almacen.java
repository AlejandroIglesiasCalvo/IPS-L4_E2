package logic.dto;

public class Producto_Almacen {
	private String ID;
	private String Nombre;
	private String Tipo;
	private double Precio;
	private int Stock;

	public Producto_Almacen(String iD, String nombre, String tipo, double precio, int stock) {
		super();
		ID = iD;
		Nombre = nombre;
		Tipo = tipo;
		Precio = precio;
		Stock = stock;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}
	
	

}
