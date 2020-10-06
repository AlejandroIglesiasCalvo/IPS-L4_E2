package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import confg.Conf;
import logic.dto.Producto;

public class GestionCreaPresupuesto {

	private static Connection con;
	private DataBase db;
	private PreparedStatement pst;
	private Statement s;

	public GestionCreaPresupuesto(Connection con, DataBase dataBase) {
		this.con = con;
		this.db = db;
	}

	/**
	 * metodo que devuelve la lista con todos lo productos que tenemos en la base de
	 * datos
	 * 
	 * @return
	 */
	public List<Producto> getProductos() {
		List<Producto> productos;
		String SQL = Conf.get("SQL_SELECCIONAR_PRODUCTOS");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			productos = toProductoList(rs);

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return productos;
	}

	private List<Producto> toProductoList(ResultSet rs) throws SQLException {
		List<Producto> res = new ArrayList();
		while (rs.next()) {
			res.add(toProductoDto(rs));
		}
		return res;
	}

	private Producto toProductoDto(ResultSet rs) throws SQLException {
		Producto dto = new Producto(Long.parseLong(rs.getString(1)), rs.getString(2), rs.getString(3), Double.parseDouble(rs.getString(4)));
		return dto;
	}

}
