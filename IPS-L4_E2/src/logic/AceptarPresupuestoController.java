package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

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
	
	private String generateId() {
		// van todas seguidas por eso el nombre de las variables
		int leftLimit = 48; // numero 0
		int rightLimit = 122; // letra z
		String id;

		Random random = new Random();

		// longitud del id
		int targetStringLength = 9;

		id = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
		return id;
	}

	
	public void crearVenta(Presupuesto p) {
		db.getGestionVentas().insertarVenta(generateId(),p);
	}


}
