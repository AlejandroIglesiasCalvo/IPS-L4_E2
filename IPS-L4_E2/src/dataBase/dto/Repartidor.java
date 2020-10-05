package dataBase.dto;

public class Repartidor {
private long ID;
private String Nombre;
private double Telefono;
public Repartidor(long iD, String nombre, double telefono) {
	super();
	ID = iD;
	Nombre = nombre;
	Telefono = telefono;
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
public double getTelefono() {
	return Telefono;
}
public void setTelefono(double telefono) {
	Telefono = telefono;
}

}
