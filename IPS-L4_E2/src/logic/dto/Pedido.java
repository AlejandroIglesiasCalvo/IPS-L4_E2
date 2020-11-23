package logic.dto;

import java.util.List;

public class Pedido {
	private String ID;
	private String Estado;
	//estos dos rellenarlos como tupla, 1 producto, x unidades
	private List<ProductoPedido> Productos;
	private Double total;

	public Pedido(String iD, String estado, List<ProductoPedido> productos, Double total) {
		super();
		ID = iD;
		Estado = estado;
		Productos = productos;
		this.total = total;
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

}
