package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import dataBase.DataBase;
import logic.dto.Pedido;
import logic.dto.Venta;

public class VisualizarGraficoController {
	
	private List<String> meses = Arrays.asList("ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMPRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE");
	
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
			String mes = p.getYear() + "-" + meses.get(p.getMonthValue()-1);
			if(!comboBox.contains(mes)) {
				comboBox.add(mes);
			}
		}
	}
	
	public String[] getComboBox(){
		return comboBox.toArray(tipos);
		
	}

	public CategoryDataset createDataset(String selected) {
		double ing = getIngresos(selected);
		double gas = getGastos(selected);
		double bal = ing-gas;
		final String ingresos = "INGRESOS";
		final String gastos = "GASTOS";
		final String balance = "BALANCE";
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(ing, selected, ingresos);

		dataset.addValue(gas, selected, gastos);

		dataset.addValue(bal, selected, balance);

		return dataset;
	}

	private double getGastos(String selected) {
		double total = 0.0;
		String[] fecha = selected.split("-");
		int año = Integer.parseInt(fecha[0]);
		int mes = meses.indexOf(fecha[1])+1;
		for(Pedido p: pedidos) {
			if(p.getFecha().getYear()==año && p.getFecha().getMonthValue()==mes) {
				total += p.getTotal();
			}
		}
		return total;
	}

	private double getIngresos(String selected) {
		double total = 0.0;
		String[] fecha = selected.split("-");
		int año = Integer.parseInt(fecha[0]);
		int mes = meses.indexOf(fecha[1])+1;
		for(Venta v: ventas) {
			if(v.getFecha().getYear()==año && v.getFecha().getMonthValue()==mes) {
				total += v.getTotal() + (v.getMontados()*5);
			}
		}
			
		return total;
	}

}
