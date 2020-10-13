package ui.presupuestos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntregasUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblTitulo;
	private JPanel pnlAbajo;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JPanel pnlCentro;
	private JPanel pnlFecha;
	private JPanel pnlHora;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JTextField txtFecha;
	private JTextField txtHora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntregasUI frame = new EntregasUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EntregasUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getLblTitulo(), BorderLayout.NORTH);
		contentPane.add(getPnlAbajo(), BorderLayout.SOUTH);
		contentPane.add(getPnlCentro(), BorderLayout.CENTER);
	}

	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel("Seleccion de decha apra la entrega");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return lblTitulo;
	}
	private JPanel getPnlAbajo() {
		if (pnlAbajo == null) {
			pnlAbajo = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlAbajo.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnlAbajo.add(getBtnAceptar());
			pnlAbajo.add(getBtnCancelar());
		}
		return pnlAbajo;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Calcelar");
		}
		return btnCancelar;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnAceptar;
	}
	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel();
			pnlCentro.setLayout(null);
			pnlCentro.add(getPnlFecha());
			pnlCentro.add(getPnlHora());
		}
		return pnlCentro;
	}
	private JPanel getPnlFecha() {
		if (pnlFecha == null) {
			pnlFecha = new JPanel();
			pnlFecha.setBounds(10, 88, 622, 30);
			pnlFecha.setLayout(new GridLayout(0, 2, 0, 0));
			pnlFecha.add(getLblFecha());
			pnlFecha.add(getTxtFecha());
		}
		return pnlFecha;
	}
	private JPanel getPnlHora() {
		if (pnlHora == null) {
			pnlHora = new JPanel();
			pnlHora.setBounds(10, 118, 622, 30);
			pnlHora.setLayout(new GridLayout(0, 2, 0, 0));
			pnlHora.add(getLblHora());
			pnlHora.add(getTxtHora());
		}
		return pnlHora;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha (dd/mm/aa)");
		}
		return lblFecha;
	}
	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora (hh:mm)");
		}
		return lblHora;
	}
	private JTextField getTxtFecha() {
		if (txtFecha == null) {
			txtFecha = new JTextField();
			txtFecha.setColumns(10);
		}
		return txtFecha;
	}
	private JTextField getTxtHora() {
		if (txtHora == null) {
			txtHora = new JTextField();
			txtHora.setColumns(10);
		}
		return txtHora;
	}
}
