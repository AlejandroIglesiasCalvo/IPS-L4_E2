package dataBase.dto;

import java.util.List;

public class Pedido {
	private long ID;
	private Boolean Estado;
	//estos dos rellenarlos como tupla, 1 producto, x unidades
	private List<Producto> Productos;
	private List<Integer> Uidades;

	public Pedido(long iD, Boolean estado, List<Producto> productos, List<Integer> uidades) {
		super();
		ID = iD;
		Estado = estado;
		Productos = productos;
		Uidades = uidades;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public Boolean getEstado() {
		return Estado;
	}

	public void setEstado(Boolean estado) {
		Estado = estado;
	}

	public List<Producto> getProductos() {
		return Productos;
	}

	public void setProductos(List<Producto> productos) {
		Productos = productos;
	}

	public List<Integer> getUidades() {
		return Uidades;
	}

	public void setUidades(List<Integer> uidades) {
		Uidades = uidades;
	}

}
