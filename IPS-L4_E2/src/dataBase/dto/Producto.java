package dataBase.dto;

public class Producto {
	private long ID;
	private String Nombre;
	private String Tipo;
	private double Precio;

	public Producto(long iD, String nombre, String tipo, double precio) {
		super();
		ID = iD;
		Nombre = nombre;
		Tipo = tipo;
		Precio = precio;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
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

}
