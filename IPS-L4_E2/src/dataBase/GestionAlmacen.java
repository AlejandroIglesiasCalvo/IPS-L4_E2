package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import confg.Conf;
import logic.dto.Pedido;
import logic.dto.ProductoPedido;
import logic.dto.Producto_Almacen;
import logic.dto.PedidoFecha;
public class GestionAlmacen {
	
	private Connection con;
	private DataBase db;
	private PreparedStatement pst;
	private Statement s;

	public GestionAlmacen(Connection con, DataBase dataBase) {
		this.con = con;
		this.db = db;
	}

	public void updateProducto(Producto_Almacen pa, int i) {
		String SQL = Conf.get("SQL_UPDATE_UNIDADES_PRODUCTO_ALMACEN");

		try {
			pst = con.prepareStatement(SQL);
			
			pst.setString(1, i + "");
			pst.setString(2, pa.getID());

			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean checkSiIdYaUtilizado(String id) {
		boolean result;
		String SQL = Conf.get("SQL_CHECK_ID_PRODUCTOS_ELEMENTOS_NO_UTILIZADO");
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

	public void creaPedido(Pedido pedido) {
		String SQL = Conf.get("SQL_CREAR_PEDIDO");

		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, pedido.getID());
			pst.setString(2, pedido.getEstado());

			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void creaEntredaPedido(String id, ProductoPedido pe) {
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
	
	public ArrayList<PedidoFecha> getPedidos() {
		ArrayList<PedidoFecha> p = new ArrayList<PedidoFecha>();
		String sql = Conf.get("SQL_GET_PEDIDOS");
		try {
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			ArrayList<ProductoPedido> pr = new ArrayList<>();
			
			String id;
			String state;
			
			LocalDateTime date;
			while(rs.next()) {
				id = rs.getString(1);
				state = rs.getString(2);
				Date d = rs.getDate(3);
				date = Instant.ofEpochMilli(d.getTime())
						.atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				System.out.println(date);
				p.add(new PedidoFecha(new Pedido(id, state, pr), date));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public void updateStatePedido(String state, String id) {
		String sql = Conf.get("SQL_SET_UPDATE_STATE");
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, state);
			pst.setString(2, id);
			pst.executeUpdate();
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
