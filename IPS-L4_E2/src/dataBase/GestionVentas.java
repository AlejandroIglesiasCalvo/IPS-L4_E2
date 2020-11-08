package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import confg.Conf;
import logic.gestionFechas;
import logic.dto.Presupuesto;
import logic.dto.Producto;
import logic.dto.Transporte;
import logic.dto.Venta;

public class GestionVentas {
	private static Connection con;
	private DataBase db;
	private PreparedStatement pst;
	private Statement s;
	private gestionFechas gf;

	public GestionVentas(Connection con, DataBase dataBase) {
		GestionVentas.con = con;
		this.db = dataBase;
	}

	public void insertarVenta(String idventa, Presupuesto p) {
		String SQL = Conf.get("SQL_INSERTAR_VENTA");
		PreparedStatement ps;
		java.sql.Date sqlDate;
		try {
			ps = con.prepareStatement(SQL);

			ps.setString(1, idventa);
			ps.setString(2, p.getID_Presupuesto());

			sqlDate = java.sql.Date.valueOf(LocalDate.now());// fecha en la que se crea la venta

			ps.setDate(3, sqlDate);

			ps.setString(4, Double.toString(p.getPrecio()));
			ps.setString(5, "0");

			ps.executeUpdate();

			p.getProductos().stream().forEach(pr -> {

				insertarProductoVenta(pr, idventa);

			});
			ps.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void insertarProductoVenta(Producto p, String idVenta) {
		String SQL = Conf.get("SQL_INSERTAR_VENTA_PRODUCTO");
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);

			ps.setString(1, p.getID());
			ps.setString(2, idVenta);
			ps.setString(3, "1");

			ps.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @return Lista de ventas
	 */
	public List<Venta> getRepartidores() {
		List<Venta> repartidores;
		String SQL = Conf.get("SQL_SELECCIONAR_VENTAS");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			repartidores = toVentasList(rs);

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return repartidores;
	}

	private List<Venta> toVentasList(ResultSet rs) throws SQLException {
		List<Venta> res = new ArrayList<Venta>();
		while (rs.next()) {
			res.add(toVentaDto(rs));
		}
		return res;
	}

	private Venta toVentaDto(ResultSet rs) throws SQLException {
		Transporte t= new Transporte();
		t.setID(Long.valueOf(rs.getString(2)));	
		LocalDate a = rs.getDate(3).toLocalDate();
		gestionFechas gf = new gestionFechas(a.getYear(),a.getMonthValue(),a.getDayOfYear(),00,00);
		Venta dto = new Venta(Long.valueOf(rs.getString(1)),gf.getFecha() ,
				Double.valueOf(rs.getString(4)),t));
		return dto;
	}
}
