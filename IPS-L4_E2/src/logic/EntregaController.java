package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import dataBase.DataBase;
import logic.dto.Presupuesto;
import logic.dto.Repartidor;
import logic.dto.Transporte;
import logic.dto.Venta;
import logic.dto.producto_venta;

public class EntregaController {
	private Presupuesto presupuesto;
	private Transporte trasnporte;
	private Venta venta;
	private Repartidor repartidor;
	private gestionFechas fecha;
	DataBase db;
	int montar;

	public EntregaController(Presupuesto presupuesto, Venta venta) {
		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		this.setPresupuesto(presupuesto);
		this.venta = venta;
		// this.trasnporte = new Transporte(598, fecha.getFecha(),
		// fecha.getHoraEnDouble(), repartidor, montar);
		fecha = new gestionFechas(LocalDateTime.now());
		this.venta.setTransporte(trasnporte);

	}

	/**
	 * Constructor con trampas mientras que no este listo el codigo que me pase los
	 * datos que necesito
	 */
	public EntregaController(Presupuesto presupuesto) {
		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		fecha = new gestionFechas(LocalDateTime.now());
		this.setPresupuesto(presupuesto);

	}

	public EntregaController() {
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
			// TODO Completar en casa
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

	public boolean Asignacion() {
		if (horarioDeTrabajo(fecha.getHoraEnDouble())) {
			int id = Integer.valueOf(generateId());
			Transporte transporte = new Transporte(id, fecha.getFecha(), fecha.getHoraEnDouble(), repartidor, montar);
			db.getGestionTransporte().añadirTransporte(transporte, venta, repartidor);
			return true;
		} else {
			return false;
		}
	}

	public Transporte getTransporteDeventa(Venta v) {
		Transporte transporte = db.getGestionTransporte().getTransporteForVentaID(v.getID());
		return transporte;
	}

	public boolean ComprobarRepartidor() {
		if (horarioDeTrabajo(fecha.getHoraEnDouble())) {
			return true;
		} else {
			return false;
		}
	}

	private boolean horarioDeTrabajo(Double Hora) {
		if (repartidor.getEntrada() <= Hora && repartidor.getSalida() > Hora) {
			return true;
		}
		return false;

	}

	/**
	 * metodo que genera el id del presupuesto
	 */
	private int generateId() {
		int randomNum = ThreadLocalRandom.current().nextInt(10, 9999 + 1);
		return randomNum;
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	public Transporte getTrasnporte() {
		return trasnporte;
	}

	public void setTrasnporte(Transporte trasnporte) {
		this.trasnporte = trasnporte;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public Repartidor getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

	public gestionFechas getFecha() {
		return fecha;
	}

	public void setFecha(gestionFechas fecha) {
		this.fecha = fecha;
	}

	public int getMontar() {
		return montar;
	}

	public void setMontar(int montar) {
		this.montar = montar;
	}

	public void guardarTransportadosYMOntados(List<producto_venta> aGuardar) {
		List<producto_venta> lista = db.getGestionTransporte().getProducto_venta();
		for (producto_venta p : aGuardar) {
			for (producto_venta j : lista) {
				if (p.getID() == j.getID() && p.getId_venta() == j.getId_venta()) {
					p.setId_venta(j.getId_venta());
					db.getGestionTransporte().guardarTyM(p);
				}
			}
		}
		/**
		 * Se actualiza el numero de montados en la venta
		 */
		db.getGestionVentas().updateUnidades(aGuardar.size(), lista.get(0).getId_venta());
	}
}
