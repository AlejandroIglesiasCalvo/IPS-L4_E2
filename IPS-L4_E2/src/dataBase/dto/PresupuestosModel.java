package dataBase.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PresupuestosModel {
	
	private static String SQL = "select id_producto, nombre, tipo, precio from producto";
	
	List<Producto> productos;
	
	private static String DRIVER = "org.hsqldb.jdbcDriver";
	private static String URL = "jdbc:hsqldb:hsql://localhost:5555";
	private static String USER = "sa";
	private static String PASS = "";
	
	public List<Producto> getProductos(){
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			c = DriverManager.getConnection(URL, USER, PASS);
			
			pst =  c.prepareStatement(SQL);
			
			rs = pst.executeQuery();
			
			productos = toProductoList(rs);
			
			rs.close();
			pst.close();
			c.close();
			
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return productos;
		
	}

	private List<Producto> toProductoList(ResultSet rs) throws SQLException {
		List<Producto> res = new ArrayList();
		while(rs.next()) {
			res.add(toProductoDto(rs));
		}
		return res;
	}

	private Producto toProductoDto(ResultSet rs) throws SQLException {
		Producto dto = new Producto(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getLong(4));
		return dto;
	}
}
