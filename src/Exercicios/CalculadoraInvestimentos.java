package Exercicios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class CalculadoraInvestimentos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				try {
					CalculadoraInvestimentos frame = new CalculadoraInvestimentos();
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
	public CalculadoraInvestimentos() {
		 setTitle("CacInvest");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 250);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Deposito Mensal R$:");
		lblNewLabel.setBounds(71, 0, 98, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(56, 24, 120, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNumDeMeses = new JLabel("Num. de meses:");
		lblNumDeMeses.setBounds(75, 55, 94, 14);
		getContentPane().add(lblNumDeMeses);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(56, 80, 120, 20);
		getContentPane().add(textField_1);
		
		JLabel lblJurosAoMes = new JLabel("Juros ao mes %:");
		lblJurosAoMes.setBounds(75, 111, 94, 14);
		getContentPane().add(lblJurosAoMes);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(56, 136, 120, 20);
		getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Calcular\r\n");
		btnNewButton.setBounds(71, 167, 89, 23);
		getContentPane().add(btnNewButton);

	}
}
