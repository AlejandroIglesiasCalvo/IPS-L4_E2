package logic;

import java.time.LocalDateTime;

import logic.dto.Presupuesto;
import logic.dto.Transporte;

public class EntregaController {
	private Presupuesto presupuesto;
	private Transporte transporte;
	private gestionFechas fecha;

	public EntregaController(Presupuesto presupuesto) {
		super();
		this.presupuesto = presupuesto;

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
		this.transporte.setFecha(presupuesto.getFecha());
		this.transporte.setHora(presupuesto.getFecha().getHour());

	}
}
