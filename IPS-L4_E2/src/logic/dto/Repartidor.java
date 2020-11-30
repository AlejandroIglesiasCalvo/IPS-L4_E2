package logic.dto;

public class Repartidor {
	private String ID;
	private String Nombre;
	private String Apellidos;
	private String Telefono;
	private double entrada, salida;
	private String ocupacion;
	private String DNI;

	public Repartidor(String string, String nombre, String string2, double entrada, double salida) {
		super();
		ID = string;
		Nombre = nombre;
		Telefono = string2;
		this.entrada = entrada;
		this.salida = salida;
	}
	
	public Repartidor(String string, String nombre, String apellidos, String tlf, double entrada, double salida, String ocupacion, String dni) {
		super();
		ID = string;
		Nombre = nombre;
		Apellidos = apellidos;
		Telefono = tlf;
		this.entrada = entrada;
		this.salida = salida;
		this.ocupacion = ocupacion;
		DNI = dni;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
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
