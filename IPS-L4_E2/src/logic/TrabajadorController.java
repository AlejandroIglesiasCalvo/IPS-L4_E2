package logic;

import java.sql.SQLException;
import java.util.Random;

import dataBase.DataBase;
import logic.dto.TrabajadorDto;

public class TrabajadorController {

	DataBase db;
	String id;

	public TrabajadorController() {
		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean addTrabajador(String nombre, String apellidos,String telefono, String entrada, String salida, String ocupacion,
			String dni) {
		generateId();
		TrabajadorDto t = new TrabajadorDto(id, nombre,apellidos, telefono, entrada, salida, ocupacion, dni);
		try {
			db.getGestionRepartidores().añadirTrabajador(t);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	private void generateId() {
		// van todas seguidas por eso el nombre de las variables
		int leftLimit = 48; // numero 0
		int rightLimit = 122; // letra z

		Random random = new Random();

		// longitud del id
		int targetStringLength = 9;

		id = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
}
