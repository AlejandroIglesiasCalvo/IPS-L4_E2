package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import confg.Conf;
import logic.gestionFechas;
import logic.dto.Repartidor;
import logic.dto.TrabajadorDto;

public class GestionRepartidores {
	private static Connection con;
	private DataBase db;
	private PreparedStatement pst;
	private Statement s;
	private gestionFechas gf;

	public GestionRepartidores(Connection conn, DataBase db) {
		con = conn;
		this.db = db;
		gf = new gestionFechas();
	}

	/**
	 * 
	 * @return Lista de respartidores
	 */
	public List<Repartidor> getRepartidores() {
		List<Repartidor> repartidores;
		String SQL = Conf.get("SQL_SELECCIONAR_REPARTIDORES");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			repartidores = toRepartidoresList(rs);

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return repartidores;
	}

	private List<Repartidor> toRepartidoresList(ResultSet rs) throws SQLException {
		List<Repartidor> res = new ArrayList<Repartidor>();
		while (rs.next()) {
			res.add(toRepartidorDto(rs));
		}
		return res;
	}

	private Repartidor toRepartidorDto(ResultSet rs) throws SQLException {
		Repartidor dto = new Repartidor(rs.getString(1), rs.getString(2), rs.getString(3),
				rs.getString(4), Double.valueOf(rs.getString(5)), Double.valueOf(rs.getString(6)),
				rs.getString(7), rs.getString(8));
		return dto;
	}

	public Repartidor getRepartidor(String id) {
		Repartidor res = null;
		String SQL = Conf.get("SQL_GET_REPARTIDOR_CON_STRING");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);

			rs = pst.executeQuery();
			
			while(rs.next()) {
				res = toRepartidorDto(rs);
			}
			

			if(rs!=null) rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return res;
	}
	public void añadirTrabajador(TrabajadorDto t) {
		String SQL = Conf.get("SQL_CREAR_TRABAJADOR");
		try {
			pst = con.prepareStatement(SQL);
			

			pst.setString(1, t.getId_empleado());
			pst.setString(2, t.getNombre());
			pst.setString(3, t.getApellidos());
			pst.setString(4, t.getTelefono());
			pst.setString(5, t.getEntrada());
			pst.setString(6, t.getSalida());
			pst.setString(7, t.getOcupacion());
			pst.setString(8, t.getDni());
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
