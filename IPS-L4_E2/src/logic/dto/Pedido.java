package logic.dto;

import java.util.List;

public class Pedido {
	private String ID;
	private String Estado;
	//estos dos rellenarlos como tupla, 1 producto, x unidades
	private List<ProductoPedido> Productos;

	public Pedido(String iD, String estado, List<ProductoPedido> productos) {
		//super();
		ID = iD;
		Estado = estado;
		Productos = productos;
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

	public void setEstado(String estado) {
		Estado = estado;
	}

	public List<ProductoPedido> getProductos() {
		return Productos;
	}

	public void setProductos(List<ProductoPedido> productos) {
		Productos = productos;
	}

}
