package logic.dto;

public class Repartidor {
	private String ID;
	private String Nombre;
	private String Telefono;
	private double entrada, salida;

	public Repartidor(String string, String nombre, String string2, double entrada, double salida) {
		super();
		ID = string;
		Nombre = nombre;
		Telefono = string2;
		this.entrada = entrada;
		this.salida = salida;
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

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public double getEntrada() {
		return entrada;
	}

	public void setEntrada(double entrada) {
		this.entrada = entrada;
	}

	public double getSalida() {
		return salida;
	}

	public void setSalida(double salida) {
		this.salida = salida;
	}

}
