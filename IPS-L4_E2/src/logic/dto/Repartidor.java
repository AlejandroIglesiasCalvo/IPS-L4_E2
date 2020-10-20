package logic.dto;

public class Repartidor {
	private String ID;
	private String Nombre;
	private String Telefono;

	public Repartidor(String string, String nombre, String string2) {
		super();
		ID = string;
		Nombre = nombre;
		Telefono = string2;
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

}
