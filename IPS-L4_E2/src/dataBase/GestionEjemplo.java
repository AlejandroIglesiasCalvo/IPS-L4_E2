//package dataBase;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//
//import confg.Conf;
//
//public class GestionEjemplo {
//	private static Connection con;
//	private DataBase db;
//	private PreparedStatement pst;
//	private Statement s;
//
//	public GestionActividades(Connection conn, DataBase db) {
//		con = conn;
//		this.db = db;
//	}
//
//	public int getUltimoId() {
//		int ultimoId = -1;
//		String SQL = Conf.get("SQL_ULTIMO_ID_ACTIVIDAD");
//		try {
//			ResultSet rs;
//			s = con.createStatement();
//			rs = s.executeQuery(SQL);
//			while (rs.next()) {
//				ultimoId = rs.getInt(1);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return ultimoId;
//	}
//
//	/**
//	 * metodo que a�ade una actividad a la BD, en este caso acorde a la historia no
//	 * dice si las plazas son limitadas o no con lo cual el numero de asistentes
//	 * sera -1
//	 * 
//	 * @param oa
//	 */
//	public void a�adirActividad(ObjetoActividad oa) {
//		String SQL = "insert into ACTIVIDAD values (?,?,?,?)";
//		try {
//			pst = con.prepareStatement(SQL);
//			int id = oa.getId();
//			String intensidad = oa.getIntensidad();
//			String tipo = oa.getTipo();
//			String descripcion = oa.getDescripcion();
//			pst.setInt(1, id);
//			pst.setString(2, tipo);
//			pst.setString(3, descripcion);
//			pst.setString(4, intensidad);
//			pst.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				con.close();
//				db.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public ObjetoActividad getActividad(int id) {
//		ResultSet rs;
//		String tipo = "", descripcion = "";
//		logic.objetos.ObjetoActividad.INTENSIDAD intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.BAJA;
//		String SQL = Conf.get("SQL_OBTENER_ACTIVIDAD");
//		try {
//			pst = con.prepareStatement(SQL);
//			pst.setInt(1, id);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				int idActividad = rs.getInt(1);
//				if (rs.getString(2).equals("ALTA"))
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.ALTA;
//				else if (rs.getString(2).equals("MEDIA"))
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.MEDIA;
//				else
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.BAJA;
//				tipo = rs.getString(3);
//				descripcion = rs.getString(4);
//				return new ObjetoActividad(idActividad, intensidad, tipo, descripcion);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//
//
//	}
//
//	public ArrayList<ObjetoActividad> getActividades() {
//		ResultSet rs;
//		ArrayList<ObjetoActividad> lista = new ArrayList<ObjetoActividad>();
//		int idActividad = 0;
//		String tipo = "", descripcion = "";
//
//		logic.objetos.ObjetoActividad.INTENSIDAD intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.BAJA;
//
//		String SQL = Conf.get("SQL_GET_ACTIVIDADES");
//		try {
//			pst = con.prepareStatement(SQL);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				idActividad = rs.getInt(1);
//				if (rs.getString(2).equals("ALTA"))
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.ALTA;
//				else if (rs.getString(2).equals("MEDIA"))
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.MEDIA;
//				else
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.BAJA;
//
//				tipo = rs.getString(3);
//				descripcion = rs.getString(4);
//				ObjetoActividad act = new ObjetoActividad(idActividad, intensidad, tipo, descripcion);
//				lista.add(act);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//
//				pst.close();
//				con.close();
//				db.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return lista;
//	}
//
//	public void addMonitorIntoActividad(int id_actividad, int id_monitor) {
//		String SQL = Conf.get("SQL_ADD_MONITOR_INTO_ACTIVIDAD");
//		try {
//			pst = con.prepareStatement(SQL);
//			pst.setInt(1, id_actividad);
//			pst.setInt(2, id_monitor);
//			pst.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	
//
//
//
//	
//	
//	
//	
//
//	public ArrayList<ObjetoMonitor> getMonitoresLibres() {
//
//
//		ResultSet rs;
//		ArrayList<ObjetoMonitor> lista = new ArrayList<ObjetoMonitor>();
//		int idMonitor = 0;
//		String nombre = "", apellido = "";
//
//		boolean baja;
//
//		String SQL = Conf.get("SQL_MONITORES_LIBRES");
//		try {
//			pst = con.prepareStatement(SQL);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				idMonitor = rs.getInt(1);
//				nombre = rs.getString(2);
//				apellido = rs.getString(2);
//
//				
//
//				baja = rs.getBoolean(4);
//				ObjetoMonitor act = new ObjetoMonitor(idMonitor, nombre, apellido, baja);
//
//				lista.add(act);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//
//				pst.close();
//				con.close();
//				db.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return lista;
//	}
//
//	public ArrayList<ObjetoUsuario> getUsuariosActividad(int id_actividad) {
//		ResultSet rs;
//		ArrayList<ObjetoUsuario> lista = new ArrayList<ObjetoUsuario>();
//		int idUsuario = 0;
//		String nombre = "", apellido = "";
//		String SQL = Conf.get("SQL_GET_USUARIOS_ACTIVIDAD");
//		try {
//			pst = con.prepareStatement(SQL);
//			pst.setInt(1, id_actividad);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				idUsuario = rs.getInt(1);
//				nombre = rs.getString(2);
//				apellido = rs.getString(3);
//
//				ObjetoUsuario act = new ObjetoUsuario(idUsuario, nombre, apellido);
//				lista.add(act);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//
//				con.close();
//				db.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//
//		return lista;
//
//	}
//
//	public ArrayList<ObjetoActividad> getActividadesConRecursos() {
//
//		ResultSet rs;
//		ArrayList<ObjetoActividad> lista = new ArrayList<ObjetoActividad>();
//		int idActividad = 0;
//		String SQL = Conf.get("SQL_GET_RECURSO_ACTIVIDAD");
//		try {
//			pst = con.prepareStatement(SQL);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				idActividad = rs.getInt(2);
//				ObjetoActividad act = getActividad(idActividad);
//				lista.add(act);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//
//				pst.close();
//				con.close();
//				db.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return lista;
//	}
//	
//	public int getRecursoPorActividad(int idActividad) {
//		int idRecurso = -1;
//		String SQL = Conf.get("SQL_GET_RECURSO");
//		try {
//			ResultSet rs;
//			pst = con.prepareStatement(SQL);
//			pst.setInt(1, idActividad);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				idRecurso = rs.getInt(1);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return idRecurso;
//	}
//	
//	public ArrayList<ObjetoActividad> getActividadesConRecursoMal() {
//		ResultSet rs;
//		ArrayList<ObjetoActividad> lista = new ArrayList<ObjetoActividad>();
//		int idActividad = 0;
//		String tipo = "", descripcion = "";
//		logic.objetos.ObjetoActividad.INTENSIDAD intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.BAJA;
//
//		String SQL = Conf.get("SQL_GET_ACTIVIDADES_CON_RECURSO");
//		try {
//			pst = con.prepareStatement(SQL);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				idActividad = rs.getInt(1);
//				if (rs.getString(2).equals("ALTA"))
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.ALTA;
//				else if (rs.getString(2).equals("MEDIA"))
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.MEDIA;
//				else
//					intensidad = logic.objetos.ObjetoActividad.INTENSIDAD.BAJA;
//
//				tipo = rs.getString(3);
//				descripcion = rs.getString(4);
//				ObjetoActividad act = new ObjetoActividad(idActividad, intensidad, tipo, descripcion);
//				lista.add(act);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//
//				pst.close();
//				con.close();
//				db.closeConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return lista;
//	}
//}
