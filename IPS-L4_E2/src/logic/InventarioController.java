package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataBase.DataBase;
import logic.dto.Producto_Almacen;
import logic.dto.Venta;

public class InventarioController {
	DataBase db;
	private ArrayList<Producto_Almacen> list;
	public InventarioController() {
		
		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Producto_Almacen> getStock() {
		list = db.getGestionInventario().getListaProductos();
		return list;
	}

}
