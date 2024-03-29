package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import logic.dto.Plantilla;

public class DataBase {
	private static Connection con;
	private static String URL = "jdbc:hsqldb:hsql://localhost:9001";
	private static String USER = "sa";
	private static String PASS = "";
	private GestionCreaPresupuesto gestionCreaPresupuesto;
	private GestionTransporte GestionTransporte;
	private GestionRepartidores GestionRepartidores;
	private GestionCliente gestionCliente;
	private GestionVentas gestionVentas;
	private GestionAlmacen gestionAlmacen;

	private GestionInventario gestionInventario;

	private GestionPedido gestionPedido;

	private GestionGrafica gestionGrafica;


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
		gestionCreaPresupuesto = new GestionCreaPresupuesto(con, this);
		GestionTransporte = new GestionTransporte(con, this);
		GestionRepartidores = new GestionRepartidores(con, this);
		gestionCliente = new GestionCliente(con, this);
		gestionVentas = new GestionVentas(con, this);
		gestionAlmacen = new GestionAlmacen(con,this);

		gestionInventario = new GestionInventario(con, this);

		gestionPedido = new GestionPedido(con,this);

		gestionGrafica = new GestionGrafica(con,this);

	}

	public GestionCreaPresupuesto getGestionCreaPresupuesto() {
		return gestionCreaPresupuesto;
	}

	public GestionTransporte getGestionTransporte() {
		return GestionTransporte;
	}

	public GestionRepartidores getGestionRepartidores() {
		return GestionRepartidores;
	}
	
	public GestionCliente getGestionCliente() {
		return gestionCliente;
	}
	
	public GestionVentas getGestionVentas() {
		return gestionVentas;
	}

	public GestionAlmacen getGestionAlmacen() {
		return gestionAlmacen;
	}

	
	public GestionInventario getGestionInventario() {
		return gestionInventario;
	}
	


	public GestionPedido getGestionCreaPedido() {
		return gestionPedido;
	}


	public GestionGrafica getGestionGrafica() {
		return gestionGrafica;
	}

}
