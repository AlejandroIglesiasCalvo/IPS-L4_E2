package logic.dto;

import java.time.LocalDateTime;

public class Transporte {
	private long ID;
	private LocalDateTime fecha;
	private double Hora;
	private Repartidor Repartidor;

	public Transporte(long iD, LocalDateTime fecha, double hora, Repartidor repartidor) {
		super();
		ID = iD;
		this.fecha = fecha;
		Hora = hora;
		this.Repartidor = repartidor;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public double getHora() {
		return Hora;
	}

	public void setHora(double hora) {
		Hora = hora;
	}

	public Repartidor getRepartidor() {
		return Repartidor;
	}

	public void setRepartidor(Repartidor repartidor) {
		this.Repartidor = repartidor;
	}

}
