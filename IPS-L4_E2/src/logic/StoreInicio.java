package logic;

import java.util.List;

import logic.dto.Cliente;
import logic.dto.Pedido;
import logic.dto.Presupuesto;
import logic.dto.Producto;
import logic.dto.ProductoCarrito;
import logic.dto.ProductoPedido;
import logic.dto.Producto_Almacen;
import logic.dto.Repartidor;
import logic.dto.TrabajadorDto;
import logic.dto.Transporte;
import logic.dto.Venta;

/***
 * 
 * @author aleja Añadir a los objetos los tipos y grupos que sean necesarios NO
 *         CONFUNDIR NOMBRES USAR SOLO COMO CLASE DE INTERCAMBIO
 */
public class StoreInicio {
	private Cliente cliente;
	private Pedido pedido;
	private Presupuesto presupuesto;
	private List<Producto_Almacen> listProductoAlmacen;
	private List<Producto> listProducto;
	private List<ProductoCarrito> listProductoCarrito;
	private List<ProductoPedido> listProductoPedido;
	private Repartidor repartidor;
	private TrabajadorDto trabajador;
	private Transporte transporte;
	private Venta venta;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Presupuesto getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(Presupuesto presupuesto) {
		this.presupuesto = presupuesto;
	}

	public List<Producto_Almacen> getListProductoAlmacen() {
		return listProductoAlmacen;
	}

	public void setListProductoAlmacen(List<Producto_Almacen> listProductoAlmacen) {
		this.listProductoAlmacen = listProductoAlmacen;
	}

	public List<Producto> getListProducto() {
		return listProducto;
	}

	public void setListProducto(List<Producto> listProducto) {
		this.listProducto = listProducto;
	}

	public List<ProductoCarrito> getListProductoCarrito() {
		return listProductoCarrito;
	}

	public void setListProductoCarrito(List<ProductoCarrito> listProductoCarrito) {
		this.listProductoCarrito = listProductoCarrito;
	}

	public List<ProductoPedido> getListProductoPedido() {
		return listProductoPedido;
	}

	public void setListProductoPedido(List<ProductoPedido> listProductoPedido) {
		this.listProductoPedido = listProductoPedido;
	}

	public Repartidor getRepartidor() {
		return repartidor;
	}

	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

	public TrabajadorDto getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(TrabajadorDto trabajador) {
		this.trabajador = trabajador;
	}

	public Transporte getTransporte() {
		return transporte;
	}

	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

}
