package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import confg.Conf;
import logic.dto.ProductoPedido;

public class GestionPedido {
	
	private static Connection con;
	private DataBase db;
	private PreparedStatement pst;
	private Statement s;

	public GestionPedido(Connection con, DataBase dataBase) {
		this.con = con;
		this.db = db;
	}

	public boolean checkSiIdYaUtilizado(String id) {
		boolean result;
		String SQL = Conf.get("SQL_CHECK_ID_PEDIDO_NO_UTILIZADO");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);

			rs = pst.executeQuery();

			if (rs.next() == true) {
				result = true;
			} else {
				result = false;
			}

			if (rs != null)
				rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	public void creaPedido(String id, Double total) {
		String SQL = Conf.get("SQL_CREAR_PEDIDO");

		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);
			pst.setString(2, "PENDIENTE");
			pst.setString(3, total+"");

			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	public void crearEntradaPedido(ProductoPedido pe, String id) {
		String SQL = Conf.get("SQL_CREAR_ENCARGO");

		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, pe.getID());
			pst.setString(2, id);
			pst.setString(3, pe.getUnidades()+"");

			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
