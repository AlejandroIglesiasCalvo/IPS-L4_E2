package dataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import confg.Conf;
import logic.gestionFechas;
import logic.dto.Repartidor;
import logic.dto.Transporte;
import logic.dto.Venta;
import logic.dto.producto_venta;

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
			String id_venta = v.getID();
			String id_repartidor = r.getID();
			Date fecha = gf.convertir_A_SQL(t.getFecha());
			String hora = String.valueOf(t.getHora());

			pst.setInt(1, id_transporte);
			pst.setString(2, id_venta);
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

	public List<Transporte> getTransportes() {
		List<Transporte> transportes;
		String SQL = Conf.get("SQL_SELECCIONAR_TRANSPORTES");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			transportes = toTransporteList(rs);

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return transportes;
	}

	private List<Transporte> toTransporteList(ResultSet rs) throws SQLException {
		List<Transporte> res = new ArrayList();
		while (rs.next()) {
			res.add(toTransporteDto(rs));
		}
		return res;
	}

	private Transporte toTransporteDto(ResultSet rs) throws SQLException {
		Transporte trans = new Transporte(rs.getLong("ID_TRANSPORTE"), rs.getTimestamp("FECHA").toLocalDateTime(),
				rs.getDouble("HORA"), getRepartidor(rs.getString("ID_REPARTIDOR")), rs.getString("ESTADO"));
		return trans;
	}

	private Repartidor getRepartidor(String id) {
		return new GestionRepartidores(con, db).getRepartidor(id);
	}

	public void updateEstado(Transporte transporte) {
		String SQL = Conf.get("SQL_UPDATE_ESTADO_TRANSPORTE");
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, transporte.getEstado());
			pst.setString(2, transporte.getID() + "");

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateEstadoAndFecha(Transporte transporte) {
		String SQL = Conf.get("SQL_UPDATE_TRANSPORTE");
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, transporte.getEstado());
			pst.setDate(2, java.sql.Date.valueOf(transporte.getFecha().toLocalDate()));
			pst.setString(3, transporte.getHora() + "");
			pst.setString(4, transporte.getID() + "");

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Transporte getTransporteForVentaID(String id) {
		Transporte t = null;
		String SQL = Conf.get("SQL_SELECCIONAR_TRANSPORTE_DE_VENTA");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			t = toTransporteDto(rs);

			rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return t;
	}

	public void guardarTyM(producto_venta p) {
		// TODO SQL_INSERTAR_VENTA_PRODUCTO_T_M

		String SQL = Conf.get("SQL_INSERTAR_VENTA_PRODUCTO_T_M");
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);

			ps.setString(1, p.getID());
			ps.setString(2, String.valueOf(p.getId_venta()));
			ps.setString(3, p.getUnidades() + "");
			ps.setInt(4, p.getTransportado());
			ps.setInt(5, p.getMontado());
			ps.executeUpdate();

			ps.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public List<producto_venta> getProducto_venta() {
		List<producto_venta> resultado = new ArrayList<>();

		String SQL = Conf.get("SQL_SELECT_VENTA_PRODUCTO");

		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);

			rs = pst.executeQuery();

			resultado = toProducto_Venta_List(rs);

			rs.close();
			pst.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	private List<producto_venta> toProducto_Venta_List(ResultSet rs) throws SQLException {
		List<producto_venta> res = new ArrayList<>();
		while (rs.next()) {
			res.add(toProducto_VentaDto(rs));
		}
		return res;
	}

	private producto_venta toProducto_VentaDto(ResultSet rs) throws SQLException {
		producto_venta dto = null;
		try {
			
		
		dto = new producto_venta(rs.getString(1), Integer.parseInt(rs.getString(2)),
				Integer.parseInt(rs.getString(3)), Integer.parseInt(rs.getString(4)),
				Integer.parseInt(rs.getString(5)));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
