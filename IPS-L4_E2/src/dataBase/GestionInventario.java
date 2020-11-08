package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.List;

import confg.Conf;
import logic.gestionFechas;
import logic.dto.Presupuesto;
import logic.dto.Producto_Almacen;
import logic.dto.Transporte;
import logic.dto.Venta;

public class GestionInventario {
	private static Connection con;
	private DataBase db;
	private PreparedStatement pst;
	private Statement s;

	public GestionInventario(Connection con, DataBase dataBase) {
		GestionInventario.con = con;
		this.db = dataBase;
	}
	
	public ArrayList<Producto_Almacen> getListaProductos() {
		String SQL = Conf.get("SQL_GET_PRODUCTOS_ALMACEN");
		
		ArrayList<Producto_Almacen> v = new ArrayList<>();
		
		String id;
		String nombre;
		String tipo;
		double precio;
		int unidades;
		
		PreparedStatement ps;
		
		try {
			
			ps = con.prepareStatement(SQL);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				id = rs.getString(1);
				nombre = rs.getString(2);
				tipo =  rs.getString(3);
				precio = rs.getDouble(4);
				unidades = rs.getInt(5);				
				
				v.add(new Producto_Almacen(id, nombre, tipo, precio, unidades));
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return v;
	}
	
}
