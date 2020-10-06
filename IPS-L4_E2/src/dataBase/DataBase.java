package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	private static Connection con;
	private static String URL = "jdbc:hsqldb:hsql://localhost:5555";
	private static String USER = "sa";
	private static String PASS = "";
	private GestionCreaPresupuesto gestionCreaPresupuesto;
	//private GestionActividades gestionActividad;
	
	// Crea conexion a base de datos
	private void setConnection() throws SQLException, ClassNotFoundException {
		con = DriverManager.getConnection(URL, USER, PASS);
	}
	
	// Cierra la conexion
	public void closeConnection() throws SQLException {
		con.close();
	}
	
	public DataBase() throws SQLException, ClassNotFoundException {
		setConnection();
		gestionCreaPresupuesto = new GestionCreaPresupuesto(con,this);
		//gestionActividad = new GestionActividades(con, this);
	}
	
	
	public GestionCreaPresupuesto getGestionCreaPresupuesto() {
		return gestionCreaPresupuesto;
	}
	
	//Conexion de ejemplo
//	public GestionActividades getGestionActividad() {
//		return gestionActividad;
//	}
	
}
