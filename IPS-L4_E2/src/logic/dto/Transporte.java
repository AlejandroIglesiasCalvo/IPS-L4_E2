package logic.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transporte {
	private long ID;
	private LocalDateTime fecha;
	private double Hora;
	private Repartidor Repartidor;
	private int Montados_en_casa;
	private String estado;
	public Transporte(long iD, LocalDateTime fecha, double hora, Repartidor repartidor, int Montados_en_casa) {
		super();
		ID = iD;
		this.fecha = fecha;
		Hora = hora;
		this.Repartidor = repartidor;
		this.setMontados_en_casa(Montados_en_casa);
	}
	
	public Transporte(long iD, LocalDateTime fecha, double hora, Repartidor repartidor, String estado) {
		super();
		ID = iD;
		this.fecha = fecha;
		Hora = hora;
		this.Repartidor = repartidor;
		this.estado = estado;
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

	public int getMontados_en_casa() {
		return Montados_en_casa;
	}

	public void setMontados_en_casa(int montados_en_casa) {
		Montados_en_casa = montados_en_casa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
