package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;

import dataBase.DataBase;
import logic.dto.Presupuesto;
import logic.dto.Repartidor;
import logic.dto.Transporte;
import logic.dto.Venta;

public class EntregaController {
	private Presupuesto presupuesto;
	private gestionFechas fecha;
	private Repartidor repartidor;
	DataBase db;
	private Venta venta;

	public EntregaController(Presupuesto presupuesto, Venta venta) {
		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.presupuesto = presupuesto;
		this.venta = venta;
		this.repartidor = new Repartidor(0, "Pedro el disponible", 2);
	}

	/**
	 * Mira si la fecha no es domingo y esta en horario de 8 a 22
	 * 
	 * @param fecha_propuesta
	 * @return si es valida o no
	 * 
	 *         NO es la misma fecha que la fecha del presupuesto
	 */
	private boolean es_fecha_valida(LocalDateTime fecha_propuesta) {
		fecha = new gestionFechas(fecha_propuesta);
		if (fecha.no_es_domingo() && fecha.esta_en_horario()) {
			return true;
		}
		return false;
	}

	public boolean asignar_reparto(LocalDateTime fecha_propuesta) {
		if (es_fecha_valida(fecha_propuesta)) {
			// Completar en casa
			return true;
		}
		return false;
	}

	public boolean comprobarFechaYHora(int año, int mes, int dia, int hora, int minutos) {
		LocalDateTime fecha = LocalDateTime.of(año, mes, dia, hora, minutos);
		if (es_fecha_valida(fecha)) {
			return true;
		}
		return false;
	}

	public void Asignacion() {
		Transporte transporte = new Transporte(99, presupuesto.getFecha(), presupuesto.getFecha().getHour(),
				repartidor);
		db.getGestionTransporte().añadirTransporte(transporte, venta, repartidor, 2);
	}
}
