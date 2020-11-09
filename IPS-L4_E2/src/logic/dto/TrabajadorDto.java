package logic.dto;

public class TrabajadorDto {
	private String id_empleado, nombre, apellidos, telefono, entrada, salida, ocupacion, dni;

	public TrabajadorDto(String id_empleado, String nombre, String apellidos, String telefono, String entrada,
			String salida, String ocupacion, String dni) {
		super();
		this.id_empleado = id_empleado;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.entrada = entrada;
		this.salida = salida;
		this.ocupacion = ocupacion;
		this.dni = dni;
	}

	public String getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(String id_empleado) {
		this.id_empleado = id_empleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

}
