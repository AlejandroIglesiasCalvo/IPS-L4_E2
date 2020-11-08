package logic;

import java.sql.SQLException;
import java.util.List;

import dataBase.DataBase;
import logic.dto.Venta;

public class VentasController {
	DataBase db;
	List<Venta> ventas;

	public VentasController() {
		
		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		ventas = db.getGestionVentas().getVentas();
	}



	public Venta getVenta(int n) {
		return ventas.get(n);
	}
	public List<Venta> getTodasLasVentas() {
		return ventas;
	}

}
