package logic.dto;

public class producto_venta extends Producto {
	private int Unidades;
	private boolean transportado;
	private boolean montado;

	public producto_venta(Producto p) {
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
