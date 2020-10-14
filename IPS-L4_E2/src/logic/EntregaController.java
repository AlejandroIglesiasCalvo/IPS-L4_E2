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
	private Transporte trasnporte;
	private Venta venta;
	private Repartidor repartidor;
	private gestionFechas fecha;
	DataBase db;


	public EntregaController(Presupuesto presupuesto, Venta venta) {
		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		this.presupuesto = presupuesto;
		this.venta = venta;
		this.repartidor = new Repartidor(0, "Pedro el disponible", 2);
	}

	/**
	 * Constructor con trampas mientras que no este listo el codigo que me pase los
	 * datos que necesito
	 */
	public EntregaController() {
		repartidor = new Repartidor(0, "Pedro el disponible", 2);
		fecha= new gestionFechas(2020, 11, 22, 17, 00);
		trasnporte= new Transporte(598, fecha.getFecha(), fecha.getHoraEnDouble(), repartidor);
		presupuesto = new Presupuesto(99, 987654321, fecha.getFecha(), 2);
		venta = new Venta((long)25, fecha.getFecha(), 52.00, 6, trasnporte);
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
