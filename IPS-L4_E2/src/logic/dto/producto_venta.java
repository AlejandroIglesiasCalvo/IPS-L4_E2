package logic.dto;

public class producto_venta {
	private int Unidades;
	private int transportado;
	private int montado;
	private String id_venta;
	private Producto p;

	public producto_venta(Producto p, int t, int m) {
		this.p = p;
		Unidades = 1;
		transportado = t;
		montado = m;
	}

	public producto_venta(Producto p) {
		this.p = p;
		Unidades = 1;
	}

	public producto_venta(String id_producto, String id_venta, int unidades, int transportados, int montados) {
		this.p= new Producto();
		this.p.setID(id_producto);
		this.id_venta = id_venta;
		this.Unidades = unidades;
		this.montado = montados;
		this.transportado = transportados;
	}

	public int getUnidades() {
		return Unidades;
	}

	public void setUnidades(int unidades) {
		Unidades = unidades;
	}

	public void setTransportado(int transportado) {
		this.transportado = transportado;
	}

	public void setMontado(int montado) {
		this.montado = montado;
	}

	public String getId_venta() {
		return id_venta;
	}

	public void setId_venta(String id_venta) {
		this.id_venta = id_venta;
	}

	public int getTransportado() {
		return transportado;
	}

	public int getMontado() {
		return montado;
	}

	public String getID() {
		return p.getID();
	}

}
