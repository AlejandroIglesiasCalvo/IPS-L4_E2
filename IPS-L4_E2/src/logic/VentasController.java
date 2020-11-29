package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataBase.DataBase;
import logic.dto.Venta;
import logic.dto.producto_venta;

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

	public List<producto_venta> getProductosMontadosYTransportados(String id) {
		List<producto_venta> resultado = db.getGestionTransporte().getProducto_venta();
		List<producto_venta> filtro = new ArrayList<>();
		for (producto_venta p : resultado) {
			if (id.equals(String.valueOf(p.getId_venta()))) {
				filtro.add(p);
			}
		}
		return filtro;
	}

}
