package logic.dto;

import java.time.LocalDateTime;

public class Presupuesto {

	private long ID_Presupuesto;
	private int DNI_Cliente;
	private LocalDateTime fecha;
	private double precio;

	public Presupuesto(long iD_Presupuesto, int dNI_Cliente, LocalDateTime fecha, double precio) {
		super();
		ID_Presupuesto = iD_Presupuesto;
		DNI_Cliente = dNI_Cliente;
		this.fecha = fecha;
		this.precio = precio;
	}

	public long getID_Presupuesto() {
		return ID_Presupuesto;
	}

	public void setID_Presupuesto(long iD_Presupuesto) {
		ID_Presupuesto = iD_Presupuesto;
	}

	public int getDNI_Cliente() {
		return DNI_Cliente;
	}

	public void setDNI_Cliente(int dNI_Cliente) {
		DNI_Cliente = dNI_Cliente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
