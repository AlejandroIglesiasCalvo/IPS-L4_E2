package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.DataBase;
import logic.dto.Cliente;
import logic.dto.Presupuesto;

public class AceptarPresupuestoController {
	
	private DataBase db;
	
	public AceptarPresupuestoController() {
		
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	

	public Cliente getClienteById(int id) {
		return db.getGestionCliente().getClienteById(id);
	}
	
	public ArrayList<Presupuesto> getPresupuestosValidos(){
		
		return db.getGestionCreaPresupuesto().getPresupuestosValidos();
	}


}
