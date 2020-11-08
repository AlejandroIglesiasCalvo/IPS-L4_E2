package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import dataBase.DataBase;
import logic.dto.Transporte;
import ui.presupuestos.EntregaPanel;

public class VisualizadorEntregasController {
	
	DataBase db;
	
	private List<Transporte> transportes;
	
	public VisualizadorEntregasController() {
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		transportes = db.getGestionTransporte().getTransportes();
		checkFechas();
	}

	private void checkFechas() {
		for(Transporte t: transportes) {
			checkFecha(t);
		}
		
	}

	public List<Transporte> getTransporte() {
		return transportes;
	}

	public void setNuevaFechaEntrega(Transporte transporte, Integer año, Integer mes, Integer dia,
			Integer horas, Integer min) {
		transporte.setEstado("PENDIENTE");
		gestionFechas gf = new gestionFechas(año, mes, dia, horas, min);
		transporte.setFecha(gf.getFecha());
		transporte.setHora(horas + (min/100));
		db.getGestionTransporte().updateEstadoAndFecha(transporte);
	}

	public void updateEstado(Transporte transporte) {
		if(transporte.getEstado().equals("PENDIENTE")) {
			transporte.setEstado("EN ENTREGA");
			db.getGestionTransporte().updateEstado(transporte);
		}else {
			transporte.setEstado("FINALIZADO");
			db.getGestionTransporte().updateEstado(transporte);
		}
			
	}

	public void checkFecha(Transporte transporte) {
		if(!transporte.getFecha().isAfter(LocalDateTime.now()) && !transporte.getEstado().equals("FINALIZADO")){
			transporte.setEstado("RETRASADO");
			db.getGestionTransporte().updateEstado(transporte);
		}
		
	}
	
}
