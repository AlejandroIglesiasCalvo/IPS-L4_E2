package logic.dto;

import java.time.LocalDateTime;

public class Venta {
	private Long ID;
	private LocalDateTime Fecha;
	private double Total;
	private int Montados_en_casa;
	private Transporte Transporte;

	public Venta(Long iD, LocalDateTime fecha, double total, int montados_en_casa, Transporte transporte) {
		super();
		ID = iD;
		Fecha = fecha;
		Total = total;
		Montados_en_casa = montados_en_casa;
		Transporte = transporte;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public LocalDateTime getFecha() {
		return Fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		Fecha = fecha;
	}

	public double getTotal() {
		return Total;
	}

	public void setTotal(double total) {
		Total = total;
	}

	public int getMontados_en_casa() {
		return Montados_en_casa;
	}

	public void setMontados_en_casa(int montados_en_casa) {
		Montados_en_casa = montados_en_casa;
	}

	public Transporte getTransporte() {
		return Transporte;
	}

	public void setTransporte(Transporte transporte) {
		Transporte = transporte;
	}

}
