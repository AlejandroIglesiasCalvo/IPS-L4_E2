package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
	private static Connection con;
	
	//private GestionActividades gestionActividad;
	
	// Crea conexi�n a base de datos
	private void setConnection() throws SQLException, ClassNotFoundException {
		String url = "jdbc:hsqldb:hsql://localhost";
		DriverManager.registerDriver(new org.hsqldb.jdbc.JDBCDriver());
		con = DriverManager.getConnection(url, "SA", "");
	}
	// Cierra la conexi�n
	public void closeConnection() throws SQLException {
		con.close();
	}
	public DataBase() throws SQLException, ClassNotFoundException {
		setConnection();
		//gestionActividad = new GestionActividades(con, this);
	}
	//Conexion de ejemplo
//	public GestionActividades getGestionActividad() {
//		return gestionActividad;
//	}
	
}
