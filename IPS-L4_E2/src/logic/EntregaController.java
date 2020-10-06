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
	//Mira si la fecha no es domingo y esta en horario de 8 a 22
	public boolean es_fecha_valida(LocalDateTime fecha_propuesta) {
		fecha = new gestionFechas(fecha_propuesta);
		if (fecha.no_es_domingo() && fecha.esta_en_horario()) {
			return true;
		}
		return false;
	}

}
