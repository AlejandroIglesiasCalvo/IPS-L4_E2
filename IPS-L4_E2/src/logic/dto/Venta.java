package logic.dto;

import java.time.LocalDateTime;

public class Venta {
	private Long ID;
	private LocalDateTime Fecha;
	private double Total;

	private Transporte Transporte;

	public Venta(Long iD, LocalDateTime fecha, double total, Transporte transporte) {
		super();
		ID = iD;
		Fecha = fecha;
		Total = total;

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


	public Transporte getTransporte() {
		return Transporte;
	}

	public void setTransporte(Transporte transporte) {
		Transporte = transporte;
	}

}
