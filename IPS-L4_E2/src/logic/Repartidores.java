package logic;

import java.sql.SQLException;
import java.util.List;

import dataBase.DataBase;
import logic.dto.Repartidor;

public class Repartidores {
	DataBase db;
	List<Repartidor> repartidores;

	public Repartidores() {
		super();
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		repartidores = db.getGestionRepartidores().getRepartidores();
	}

	public List<Repartidor> getRepartidores() {
		return repartidores;
	}

	public Repartidor getRepartidor(int n) {
		return repartidores.get(n);
	}

}
