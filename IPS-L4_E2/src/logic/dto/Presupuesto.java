package logic.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Presupuesto {

	private String ID_Presupuesto;
	private String DNI_Cliente;
	private LocalDateTime fecha;
	private double precio;
	private List<ProductoCarrito> productos = new ArrayList<>();

	public Presupuesto(String iD_Presupuesto, String dNI_Cliente, LocalDateTime fecha, double precio,
			List<ProductoCarrito> productosEnPresupuesto) {
		super();
		ID_Presupuesto = iD_Presupuesto;
		DNI_Cliente = dNI_Cliente;
		this.fecha = fecha;
		this.precio = precio;
		this.productos = productosEnPresupuesto;
	}

	public List<ProductoCarrito> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoCarrito> productos) {
		this.productos = productos;
	}

	public String getID_Presupuesto() {
		return ID_Presupuesto;
	}

	public void setID_Presupuesto(String iD_Presupuesto) {
		ID_Presupuesto = iD_Presupuesto;
	}

	public String getDNI_Cliente() {
		return DNI_Cliente;
	}

	public void setDNI_Cliente(String dNI_Cliente) {
		DNI_Cliente = dNI_Cliente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
