package logic.dto;

public class Producto {
	private String ID;
	private String Nombre;
	private String Tipo;
	private double Precio;

	public Producto(String iD, String nombre, String tipo, double precio) {
		super();
		ID = iD;
		Nombre = nombre;
		Tipo = tipo;
		Precio = precio;
	}

	public Producto() {
		// TODO Auto-generated constructor stub
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

}
