package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataBase.DataBase;
import logic.dto.Pedido;
import logic.dto.PedidoFecha;
import logic.dto.Producto_Almacen;

public class PedidosController {
	DataBase db;
	private ArrayList<Pedido> list;
	
	public PedidosController() {		
		super();
		System.out.println("hola");
		try {
			db = new DataBase();
			System.out.println("hola");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("error");
			e.printStackTrace();
		}catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		
		System.out.println("hola");
	}

	public ArrayList<Pedido> getListaPedidos() {
		list = db.getGestionAlmacen().getPedidos();
		return list;
	}
	public void updatePedidoState(String state, int row) {
		db.getGestionAlmacen().updateStatePedido(state, list.get(row));
	}

}