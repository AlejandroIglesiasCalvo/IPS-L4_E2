package logic.dto;

public class Cliente {
	private int ID;
	private String Nombre;
	private String Apellidos;
	private int Telefono;
	private String direccion;
	
	public Cliente(int iD, String nombre, String apellidos, int telefono, String direccion) {
		super();
		ID = iD;
		Nombre = nombre;
		Apellidos = apellidos;
		Telefono = telefono;
		this.direccion = direccion;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
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
		return "DNI: " + ID +" Nombre: "+ Nombre +" "+ Apellidos + " Telefono: "+ Telefono +".";
	}
}
