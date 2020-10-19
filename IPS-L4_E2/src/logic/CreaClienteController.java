package logic;

import java.sql.SQLException;
import java.util.ArrayList;

import dataBase.DataBase;
import logic.dto.Cliente;

public class CreaClienteController {
	
	private DataBase db;
	
	public CreaClienteController() {
		
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void CrearNuevoCliente(Cliente c) {
		db.getGestionCreaCliente().CrearClienteNuevo(c);		
	}
	
	public ArrayList<Cliente> showClientes(){
		return db.getGestionCreaCliente().getListaClientes();
	}


}
