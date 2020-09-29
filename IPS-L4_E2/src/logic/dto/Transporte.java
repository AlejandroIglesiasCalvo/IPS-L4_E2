package logic.dto;

import java.sql.Date;

public class Transporte {
	private long ID;
	private Date fecha;
	private double Hora;
	private Repartidor repartidor;

	public Transporte(long iD, Date fecha, double hora, Repartidor repartidor) {
		super();
		ID = iD;
		this.fecha = fecha;
		Hora = hora;
		this.repartidor = repartidor;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getHora() {
		return Hora;
	}

	public void setHora(double hora) {
		Hora = hora;
	}

	public Repartidor getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

}
