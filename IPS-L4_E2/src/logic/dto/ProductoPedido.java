package logic.dto;

public class ProductoPedido extends Producto{

	private int Unidades;
	
	public ProductoPedido(Producto_Almacen p, int unidades) {
		super(p.getID(), p.getNombre(), p.getTipo(), p.getPrecio());
		Unidades = unidades;
	}
	
	public int getUnidades() {
		return Unidades;
	}

	public void setUnidades(int unidades) {
		Unidades = unidades;
	}

}
