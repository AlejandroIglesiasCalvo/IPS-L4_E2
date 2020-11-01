package logic.dto;

public class ProductoCarrito extends Producto{

	private int Unidades;
	
	public ProductoCarrito(Producto p) {
		super(p.getID(), p.getNombre(), p.getTipo(), p.getPrecio());
		Unidades = 1;
	}
	
	public int getUnidades() {
		return Unidades;
	}

	public void setUnidades(int unidades) {
		Unidades = unidades;
	}

}
