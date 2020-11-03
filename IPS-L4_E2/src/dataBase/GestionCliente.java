package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import confg.Conf;
import logic.dto.Cliente;
import logic.dto.Producto;

public class GestionCliente {
	private Connection con;
	private DataBase db;
	
	
	public GestionCliente(Connection con, DataBase dataBase) {
			this.con = con;
			this.db = db;
	}
	
	public void CrearClienteNuevo(Cliente c) {
		String SQL = Conf.get("SQL_CREAR_CLIENTE");
		PreparedStatement pst;
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, c.getID());
			pst.setString(2, c.getNombre());
			pst.setString(3, c.getApellidos());
			pst.setString(4, Integer.toString(c.getTelefono()));
			pst.setString(5, c.getDireccion());
			
			pst.execute();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public ArrayList<Cliente> getListaClientes() {
		String SQL = Conf.get("SQL_SELECCIONAR_CLIENTES");
		PreparedStatement pst;
		ArrayList<Cliente> list = new ArrayList<>();
		
		String id;
		String nombre;
		String apellidos;
		int telefono;
		String direccion;
		Cliente c;
		
		try {
			pst = con.prepareStatement(SQL);			
			pst.execute();
			
			ResultSet rs = pst.getResultSet();
			while(rs.next()) {
				id = rs.getString(1);
				nombre = rs.getString(2);
				apellidos = rs.getString(3);
				telefono = Integer.valueOf(rs.getString(4));
				direccion= rs.getString(5);
				c = new Cliente(id, nombre, apellidos, telefono, direccion);				
				list.add(c);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;		
	} 
	
	public Cliente getClienteById(String id) {
		String SQL = Conf.get("SQL_SELECCIONAR_CLIENTE_BY_ID");
		
		PreparedStatement pst;
		Cliente c = null;		
		String nombre;
		String apellidos;
		int telefono;
		String direccion;
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);
			pst.execute();
			
			ResultSet rs = pst.getResultSet();
			while(rs.next()) {			
				nombre = rs.getString(1);
				apellidos = rs.getString(2);
				telefono = Integer.valueOf(rs.getString(3));
				direccion = rs.getString(4);
				c = new Cliente(id, nombre, apellidos, telefono, direccion);				
			}
			pst.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return c;		
	}
	

}
