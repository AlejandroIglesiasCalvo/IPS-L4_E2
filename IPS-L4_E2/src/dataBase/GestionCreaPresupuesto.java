package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import confg.Conf;
import logic.dto.Cliente;
import logic.dto.Presupuesto;
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
		Producto dto = new Producto(rs.getString(1), rs.getString(2), rs.getString(3), Double.parseDouble(rs.getString(4)));
		return dto;
	}

	/**
	 * metodo que mira si el id del presupuesto ya est� siendo utilizado en la base de datos
	 * devuelve true cuando hay un id igual y false cuando no
	 * @param id
	 * @return
	 */
	public boolean checkSiIdYaUtilizado(String id) {
		boolean result;
		String SQL = Conf.get("SQL_CHECK_ID_PRODUCTOS_NO_UTILIZADO");
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);

			rs = pst.executeQuery();
			
			if(rs.next() == true) {
				result = true;
			}else {
				result = false;
			}

			if(rs!=null) rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * mira si ya tenemos el producto en el carrito
	 * @param p
	 * @param id
	 * @return
	 */
	public boolean checkYaTenemosProducto(Producto p, String id) {
		boolean result;
		String SQL = Conf.get("SQL_CHECK_PRESUPUESTO_TIENE_PRODUCTO");
		
		try {
			ResultSet rs;
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);
			pst.setString(2, p.getID());

			rs = pst.executeQuery();
			
			if(rs.next() == true) {
				result = true;
			}else {
				result = false;
			}

			if(rs!=null) rs.close();
			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * hace un update en las unidades del producto seleccionado
	 * en el primer sprint no nos tenemos que hacer cargo de las unidades, 
	 * tenemos que hacer el update
	 * @param p
	 * @param id
	 */
	public void UpdateUnidadesProducto(Producto p, String id) {
		String SQL = Conf.get("SQL_UPDATE_UNIDADES_PRODUCTO");
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);
			pst.setString(2, p.getID());

			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}

	/**
	 * crea una entrada en la tabla carrito con el producto y el id
	 * del presupuesto
	 * @param p
	 * @param id
	 */
	public void CrearEntradaPresupuesto(Producto p,String id) {
		String SQL = Conf.get("SQL_CREA_LINEA_CARRITO");
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, p.getID());
			pst.setString(2, id);
			pst.setInt(3, 1);
			
			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * crea un presupuesto con toda su informacion
	 * @param id
	 * @param total
	 */
	public void CreaPresupuesto(String id, double total) {
		String SQL = Conf.get("SQL_CREAR_PRESUPUESTO");
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);
			//no hay cliente, por eso meto una string cualquiera
			pst.setString(2, "");
			long millis = System.currentTimeMillis();
			pst.setDate(3, new java.sql.Date(millis));
			pst.setString(4, Double.toString(total));
			
			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void CreaPresupuestoConCliente(String id, Cliente c, double total) {
		String SQL = Conf.get("SQL_CREAR_PRESUPUESTO");
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, id);
			pst.setString(2, Long.toString(c.getID()));
			long millis = System.currentTimeMillis();
			pst.setDate(3, new java.sql.Date(millis));
			pst.setString(4, Double.toString(total));
			
			pst.executeUpdate();

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<Presupuesto> getPresupuestosValidos() {
		String SQL = Conf.get("SQL_SELECCIONAR_PRESUPUESTO_VALIDO");
		
		ArrayList<Presupuesto> sol = new ArrayList<Presupuesto>();
		Presupuesto p;
		int dni;
		String id_Pres;
		LocalDate date;
		LocalDateTime fecha;
		double precio;
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, LocalDate.now().minusDays(15).toString());	
			pst.execute();
			
			System.out.println(LocalDate.now().minusDays(15).toString());
			
			ResultSet rs = pst.getResultSet();
			while(rs.next()) {
				id_Pres = rs.getString(1);
				dni = Integer.valueOf(rs.getString(2));
				date = LocalDate.parse(rs.getString(3));
				fecha = LocalDateTime.of(date,LocalTime.now());
				
				precio = Double.valueOf(rs.getString(4));
				p = new Presupuesto(id_Pres, dni, fecha, precio, getProductosPresupuesto(id_Pres));
				
				sol.add(p);				
			}

			pst.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return sol;
	}
	/**
	 * Debuelbe una lista con las id's de los productos asociados a un presupuesto
	 * @param id_presupuesto
	 * @return
	 */
	public ArrayList<Producto> getProductosPresupuesto(String id_presupuesto){
		String SQL = Conf.get("SQL_SELECCIONAR_PRODUCTOS_PRESUPUESTO");
		ArrayList<Producto> l = new ArrayList<>();
		
		String id;
		String nombre;
		String tipo;
		double precio;
		
		try {
			pst = con.prepareStatement(SQL);
			pst.setString(1, id_presupuesto);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				id = rs.getString(1);
				nombre = rs.getString(2);
				tipo = rs.getString(3);
				precio = Double.valueOf(rs.getString(4));
				
				l.add(new Producto(id, nombre, tipo, precio));
			}
			rs.close();
			pst.close();			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return l;		
	}
	

}
