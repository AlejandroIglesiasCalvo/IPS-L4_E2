package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.category.CategoryDataset;

import dataBase.DataBase;
import logic.dto.Pedido;
import logic.dto.Venta;

public class VisualizarGraficoController {
	
	private String meses[] = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMPRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
	
	private List<String> comboBox = new ArrayList<>();
	
	private DataBase db;
	
	private List<Pedido> pedidos;
	private List<Venta> ventas;
	private List<LocalDateTime> fechas;
	
	private String[] tipos = new String[comboBox.size()];
	
	public VisualizarGraficoController() {
		try {
			db = new DataBase();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		pedidos = db.getGestionGrafica().getPedidos();
		ventas = db.getGestionGrafica().getVentas();
		fechas = db.getGestionGrafica().getFechas();
		
		cargarComboBox();
	}

	private void cargarComboBox() {
		comboBox.add("Sin definir");
		for(LocalDateTime p: fechas) {
			String mes = p.getYear() + "-" + meses[p.getMonthValue()-1];
			if(!comboBox.contains(mes)) {
				comboBox.add(mes);
			}
		}
	}
	
	public String[] getComboBox(){
		return comboBox.toArray(tipos);
		
	}

	public CategoryDataset createDataset(String selected) {
		// TODO Auto-generated method stub
		return null;
	}

}
