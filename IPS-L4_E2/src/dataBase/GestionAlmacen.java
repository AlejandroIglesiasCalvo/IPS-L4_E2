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
			pst.setString(3, pedido.getTotal()+"");
			long millis = System.currentTimeMillis();
			pst.setDate(4, new java.sql.Date(millis));

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
	
	public ArrayList<Pedido> getPedidos() {
		ArrayList<Pedido> p = new ArrayList<Pedido>();
		String sql = Conf.get("SQL_GET_PEDIDOS");
		try {
			pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			ArrayList<ProductoPedido> pr;
			
			String id;
			String state;
			
			LocalDateTime date;
			
			double total;
			
			Pedido pedido;
			while(rs.next()) {
				id = rs.getString(1);
				
				pr =  getProductosPedido(id);
				
				state = rs.getString(2);
				Date d = rs.getDate(3);
				date = Instant.ofEpochMilli(d.getTime())
						.atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				total = rs.getDouble(4);
				//System.out.println(date);
				pedido = new Pedido(id, state, pr, total, date);
				p.add(pedido);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	private ArrayList<ProductoPedido> getProductosPedido(String id){
		ArrayList<ProductoPedido> sol = new ArrayList<>();
		String sql = Conf.get("SQL_GET_PRODUCTOR_PEDIDO");//lista de productos
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rs = pst.executeQuery();
			
			String iD;
			String nombre;
			String tipo;
			double precio;
			int unidades;
			Producto_Almacen pa;
			
			while(rs.next()) {
				
				iD = rs.getString(1);
				nombre = rs.getString(2);
				tipo = rs.getString(3);
				precio = rs.getDouble(4);
				unidades = rs.getInt(5);
				pa = new Producto_Almacen(iD, nombre, tipo, precio, 0);
				
				sol.add(new ProductoPedido(pa, unidades));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sol;
	}
	
	public void updateStatePedido(String state, Pedido pedido) {
		String sql = Conf.get("SQL_SET_UPDATE_STATE");
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, state);
			pst.setString(2, pedido.getID());
			pst.executeUpdate();
			
			updateStock(pedido);
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void updateStock(Pedido idPedido) {
					
		int units;
		Producto_Almacen pa;
		try {
			
		List<ProductoPedido> pp = idPedido.getProductos();
		for(ProductoPedido p : idPedido.getProductos()) {
			units = getUnidadesProducto(p);
			pa = new Producto_Almacen(p.getID(), p.getNombre(), p.getTipo(), p.getPrecio(), units);
			updateProducto(pa, units + p.getUnidades());
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getUnidadesProducto(ProductoPedido p) {
		int sol=0;
		String sql = Conf.get("SQL_GET_UNIDADES_PERODUCTO_AMACEN");
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, p.getID());
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				sol = rs.getInt(1);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return sol;
	}
	
	

}
