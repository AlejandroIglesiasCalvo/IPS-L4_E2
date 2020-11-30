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
import logic.dto.Producto;
import logic.dto.ProductoCarrito;
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

			sqlDate = java.sql.Date.valueOf(d.toLocalDate());
			ps.setDate(3, sqlDate);

			v = new Venta(idventa, d, p.getPrecio());
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

		return v;
	}

	/*
	 * Hay que cambiar en este metodo el numero de productos que se le asigna a la
	 * venta
	 */
	private void insertarProductoVenta(ProductoCarrito p, String idVenta) {
		String SQL = Conf.get("SQL_INSERTAR_VENTA_PRODUCTO");
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);

			ps.setString(1, p.getID());
			ps.setString(2, idVenta);
			ps.setString(3, p.getUnidades() + "");
			ps.setString(4, "0");
			ps.setString(5, "0");
			ps.executeUpdate();

			ps.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @return Lista de ventas
	 */
	public List<Venta> getVentas() {
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
		Transporte t = new Transporte();
		LocalDate a = rs.getDate(3).toLocalDate();
		gestionFechas gf = new gestionFechas(a.getYear(), a.getMonthValue(), a.getDayOfMonth(), 00, 00);
		Venta dto = new Venta(rs.getString(1), gf.getFecha(), Double.valueOf(rs.getString(4)), t);
		return dto;
	}

	public String getNombreProductoPorID(String id) {
		String result = "";
		String sql = Conf.get("SQL_GET_NOMBRE_POR_ID");
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id);

			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getString(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public void updateUnidades(int size, String id) {
		String SQL = Conf.get("SQL_UPDATE_UNIDADES_MONTADOS_EN_VENTA");

		try {
			pst = con.prepareStatement(SQL);

			pst.setString(1, String.valueOf(size));
			pst.setString(2, String.valueOf(id));
			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
