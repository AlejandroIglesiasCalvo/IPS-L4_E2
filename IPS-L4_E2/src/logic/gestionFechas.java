package logic;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class gestionFechas {
	private LocalDateTime fecha;
	private LocalTime hora;
	private LocalTime entrada = LocalTime.of(8, 00);
	private LocalTime salida = LocalTime.of(20, 00);

	/**
	 * Pasar una localDateTime
	 * 
	 */
	public gestionFechas(LocalDateTime fecha) {
		super();
		this.fecha = fecha;
		this.hora = LocalTime.of(fecha.getHour(), 00);
	}
	public gestionFechas() {
		super();
	}

	/**
	 * Se crea asi: todo ints (meses MONTH.January, por ejemplo, horas y minutos en
	 * "00" Osea, las 4:04 am son las 04,04
	 * 
	 */
	public gestionFechas(int año, int mes, int dia, int hora, int minutos) {
		super();
		this.fecha = LocalDateTime.of(año, mes, dia, hora, minutos);
		this.hora = LocalTime.of(fecha.getHour(), 00);
	}

	/** Devuelve la fecha en formato entero */
	public LocalDateTime getFecha() {
		return fecha;
	}

	/** Cambia la fecha en formato entero */
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	/** Devuelve la hora de inicio de los repartos */
	public LocalTime getEntrada() {
		return entrada;
	}

	/** Devuelve la hora de finalizacion de los repartos */
	public LocalTime getSalida() {
		return salida;
	}

	/** Devuelve la hora de la fecha dada, en formato LocalTime (horas, minutos) */
	public LocalTime getHoraDeFecha() {
		LocalTime hora = LocalTime.of(fecha.getHour(), fecha.getMinute());
		return hora;
	}
	
	public double getHoraEnDouble() {
		return fecha.getHour();
	}

	/** Devuelve el dia de la semana de la fecha */
	public DayOfWeek getDiaSemana() {
		DayOfWeek dia = fecha.getDayOfWeek();
		return dia;
	}

	/** Comprueba que no es domingo */
	public boolean no_es_domingo() {

		if (fecha.getDayOfWeek() != DayOfWeek.SUNDAY) {
			return true;
		}
		return false;
	}

	/** Esta en horario de reparto */
	public boolean esta_en_horario() {

		if (hora.isAfter(entrada) && hora.isBefore(salida)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param fecha
	 * @return la fecha en formato SQL lista para insertar
	 */
	public java.sql.Date convertir_A_SQL(LocalDateTime fecha) {
		java.sql.Date sqlDate = java.sql.Date.valueOf(fecha.toLocalDate());
		return sqlDate;
	}

}
