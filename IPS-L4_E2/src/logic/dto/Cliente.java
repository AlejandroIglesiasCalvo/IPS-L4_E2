package logic.dto;

public class Cliente {
	private String ID;
	private String Nombre;
	private String Apellidos;
	private int Telefono;
	private String direccion;
	
	public String getDireccion() {
		return direccion;
	}
	public Cliente(String iD, String nombre, String apellidos, int telefono, String direccion) {
		super();
		ID = iD;
		Nombre = nombre;
		Apellidos = apellidos;
		Telefono = telefono;
		this.direccion = direccion;
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
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}
	public int getTelefono() {
		return Telefono;
	}
	public void setTelefono(int telefono) {
		Telefono = telefono;
	}
	
	public String toString(){
		String r = "DNI: " + ID +" Nombre: "+ Nombre +" "+ Apellidos + " Telefono: "+ Telefono;
		if(this.direccion != null) {
			r+= "Direccion: " + direccion;
		}
		return r + " .";
	}
}
