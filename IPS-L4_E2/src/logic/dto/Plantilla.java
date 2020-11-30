package logic.dto;

import java.time.LocalDateTime;
import java.util.List;

public class Plantilla extends Presupuesto {
	String nombre;
	
	public Plantilla(Presupuesto p , String nombre) {
		super(p.getID_Presupuesto(), p.getDNI_Cliente(),p.getFecha(),p.getPrecio(),p.getProductos());
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	

}
