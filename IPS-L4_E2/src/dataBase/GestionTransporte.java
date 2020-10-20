package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import confg.Conf;
import logic.gestionFechas;
import logic.dto.Producto;
import logic.dto.Repartidor;
import logic.dto.Transporte;
import logic.dto.Venta;

public class GestionTransporte {
	private static Connection con;
	private DataBase db;
	private PreparedStatement pst;
	private Statement s;
	private gestionFechas gf;

	public GestionTransporte(Connection conn, DataBase db) {
		con = conn;
		this.db = db;
		gf = new gestionFechas();
	}

	public void añadirTransporte(Transporte t, Venta v, Repartidor r) {
		String SQL = Conf.get("SQL_CREAR_TRANSPORTE");
		try {
			pst = con.prepareStatement(SQL);
			int id_transporte = (int) t.getID();
			int id_venta = v.getID().intValue();
			String id_repartidor =  r.getID();
			Date fecha = gf.convertir_A_SQL(t.getFecha());
			String hora = String.valueOf(t.getHora());

			pst.setInt(1, id_transporte);
			pst.setInt(2, id_venta);
			pst.setInt(3, t.getMontados_en_casa());
			pst.setString(4, hora);
			pst.setDate(5, fecha);
			pst.setString(6, id_repartidor);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				db.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
