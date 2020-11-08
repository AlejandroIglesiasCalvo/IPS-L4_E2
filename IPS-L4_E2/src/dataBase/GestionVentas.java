package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;

import confg.Conf;
import logic.dto.Presupuesto;
import logic.dto.Producto;
import logic.dto.Venta;

public class GestionVentas {
	Connection con;
	DataBase db;
	
	public GestionVentas(Connection con, DataBase dataBase) {
		this.con = con;
		this.db = dataBase;
	}
	
	public Venta insertarVenta(String idventa, Presupuesto p) {
		String SQL = Conf.get("SQL_INSERTAR_VENTA");
		
		Venta v = null;
		
		PreparedStatement ps;
		java.sql.Date sqlDate;
		try {
			ps = con.prepareStatement(SQL);
			
			ps.setString(1, idventa);
			ps.setString(2, p.getID_Presupuesto());
			
			LocalDateTime d = LocalDateTime.now();
			
			sqlDate = java.sql.Date.valueOf(d.toLocalDate());//fecha en la que se crea la venta
			
			ps.setDate(3, sqlDate);
			
			v = new Venta(idventa, d, p.getPrecio());
			
			ps.setString(4, Double.toString(p.getPrecio()));
			ps.setString(5, "0");
			
			ps.executeUpdate();
			
			p.getProductos().stream().forEach(pr -> {
				
				insertarProductoVenta(pr, idventa);
				
			});
			ps.close();
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return v;
	}
	/*
	 * Hay que cambiar en este metodo el numero de productos que se le asigna a la venta
	 */
	private void insertarProductoVenta(Producto p, String idVenta) {
		String SQL = Conf.get("SQL_INSERTAR_VENTA_PRODUCTO");
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);
			
			ps.setString(1, p.getID());
			ps.setString(2, idVenta);
			ps.setString(3, "1");
			
			ps.executeUpdate();
			
			ps.close();
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
