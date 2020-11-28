package logic.dto;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
	private String ID;
	private String Estado;
	//estos dos rellenarlos como tupla, 1 producto, x unidades
	private List<ProductoPedido> Productos;
	private Double total;
	private LocalDateTime Fecha; 


	/*public Pedido(String iD, String estado, List<ProductoPedido> productos) {
		//super();*/

	public Pedido(String iD, String estado, List<ProductoPedido> productos, Double total, LocalDateTime fecha) {
		super();
		ID = iD;
		Estado = estado;
		Productos = productos;
		this.total = total;
		Fecha = fecha;
	}
	
	public Pedido(String id, String estado, Double total, LocalDateTime fecha) {
		super();
		ID = id;
		Estado = estado;
		this.total = total;
		Fecha = fecha;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getEstado() {
		return Estado;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public LocalDateTime getFecha() {
		return Fecha;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public List<ProductoPedido> getProductos() {
		return Productos;
	}

	public void setProductos(List<ProductoPedido> productos) {
		Productos = productos;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	
	public void setFecha(LocalDateTime fecha) {
		this.Fecha = fecha;
	}

}
