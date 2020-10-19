package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import confg.Conf;
import logic.dto.Cliente;
import logic.dto.Producto;

public class GestionCreaCliente {
	private Connection con;
	private DataBase db;
	
	
	public GestionCreaCliente(Connection con, DataBase dataBase) {
			this.con = con;
			this.db = db;
	}
	
	public void CrearClienteNuevo(Cliente c) {
		String SQL = Conf.get("SQL_CREAR_CLIENTE");
		PreparedStatement pst;
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, Long.toString(c.getID()));
			pst.setString(2, c.getNombre());
			pst.setString(3, c.getApellidos());
			pst.setString(4, Integer.toString(c.getTelefono()));
			
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
		
		long id;
		String nombre;
		String apellidos;
		int telefono;
		Cliente c;
		
		try {
			pst = con.prepareStatement(SQL);			
			pst.execute();
			
			ResultSet rs = pst.getResultSet();
			while(rs.next()) {
				id = Long.valueOf(rs.getString(1));
				nombre = rs.getString(2);
				apellidos = rs.getString(3);
				telefono = Integer.valueOf(rs.getString(4));
				c = new Cliente(id, nombre, apellidos, telefono);				
				list.add(c);
			}
			pst.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;		
	} 
	

}
