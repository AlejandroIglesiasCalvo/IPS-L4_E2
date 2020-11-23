package logic.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoFecha extends Pedido{
	//private String ID;
	//private String Estado;
	//estos dos rellenarlos como tupla, 1 producto, x unidades
	private List<ProductoPedido> Productos;
	private LocalDateTime date;

	public PedidoFecha(Pedido p, LocalDateTime date) {
		super(p.getID(),p.getEstado(),p.getProductos());
		this.date = date;
	}

	public List<ProductoPedido> getProductos() {
		return Productos;
	}

	public void setProductos(List<ProductoPedido> productos) {
		Productos = productos;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	

}
