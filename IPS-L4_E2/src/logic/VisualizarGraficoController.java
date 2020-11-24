package logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataBase.DataBase;
import logic.dto.Pedido;
import logic.dto.Venta;

public class VisualizarGraficoController {
	
	private String meses[] = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMPRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
	
	private List<String> comboBox = new ArrayList<>();
	
	private DataBase db;
	
	private List<Pedido> pedidos;
	private List<Venta> ventas;
	
	private String[] tipos = new String[comboBox.size()];
	
	public VisualizarGraficoController() {
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		pedidos = db.getGestionGrafica().getPedidos();
		ventas = db.getGestionGrafica().getVentas();
		
		cargarComboBox();
	}

	private void cargarComboBox() {
		for(Pedido p: pedidos) {
			String mes = p.getFecha().getYear() + "-" + meses[p.getFecha().getMonthValue()-1];
			if(!comboBox.contains(mes)) {
				comboBox.add(mes);
			}
		}
		for(Venta v: ventas) {
			String mes = v.getFecha().getYear() + "-" + meses[v.getFecha().getMonthValue()-1];
			if(!comboBox.contains(mes)) {
				comboBox.add(mes);
			}
		}
	}
	
	public String[] getComboBox(){
		return comboBox.toArray(tipos);
		
	}

}
