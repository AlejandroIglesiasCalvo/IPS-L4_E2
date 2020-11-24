package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import confg.Conf;
import logic.dto.Pedido;
import logic.dto.Venta;

public class GestionGrafica {
	
	private static Connection con;
	private DataBase db;
	private PreparedStatement pst;
	private Statement s;

	public GestionGrafica(Connection con, DataBase dataBase) {
		this.con = con;
		this.db = db;
	}

	public List<Pedido> getPedidos(){
		List<Pedido> pedidos;
		String SQL = Conf.get("SQL_SELECCIONAR_PEDIDOS");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			pedidos = toPedidiosList(rs);

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return pedidos;
	}
	
	private List<Pedido> toPedidiosList(ResultSet rs) throws SQLException {
		List<Pedido> res = new ArrayList<>();
		while(rs.next()) {
			res.add(toPedidoDto(rs));
		}
		return res;
	}

	private Pedido toPedidoDto(ResultSet rs) throws NumberFormatException, SQLException {
		Pedido dto = new Pedido(rs.getString(1), rs.getString(2), Double.parseDouble(rs.getString(3)),
				rs.getTimestamp(4).toLocalDateTime());
		return dto;
	}

	public List<Venta> getVentas(){
		List<Venta> ventas;
		String SQL = Conf.get("SQL_SELECCIONAR_VENTAS");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			ventas = toVentasList(rs);

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return ventas;
		
	}

	private List<Venta> toVentasList(ResultSet rs) throws SQLException {
		List<Venta> res = new ArrayList<>();
		while(rs.next()) {
			res.add(toVentaDto(rs));
		}
		return res;
	}

	private Venta toVentaDto(ResultSet rs) throws NumberFormatException, SQLException {
		Venta dto = new Venta(rs.getString(1),rs.getTimestamp(3).toLocalDateTime(),Double.parseDouble(rs.getString(4)));
		return dto;
	}

	public List<LocalDateTime> getFechas() {
		List<LocalDateTime> fechas;
		String SQL = Conf.get("SQL_SELECCIONAR_FECHAS");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			fechas = toFechasList(rs);

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return fechas;
	}

	private List<LocalDateTime> toFechasList(ResultSet rs) throws SQLException {
		List<LocalDateTime> res = new ArrayList<>();
		while(rs.next()) {
			res.add(rs.getTimestamp(1).toLocalDateTime());
		}
		return res;
	}
}
