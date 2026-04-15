package Exercicios;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculadoraInvestimentosGrid2 extends JFrame {
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;

    public CalculadoraInvestimentosGrid2() {
        setTitle("CalcInvest");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(5, 2));
        setContentPane(contentPane);

        
        contentPane.add(new JLabel("Depósito mensal R$:"));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(p1);
        
        textField1 = new JTextField();
        p1.add(textField1);
        textField1.setColumns(10);

       
        contentPane.add(new JLabel("Num. de meses:"));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(p2);
        
        textField2 = new JTextField();
        p2.add(textField2);
        textField2.setColumns(10);

       
        contentPane.add(new JLabel("Juros ao mês %:"));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(p3);
        
        textField3 = new JTextField();
        p3.add(textField3);
        textField3.setColumns(10);

        
        contentPane.add(new JLabel("Total investido + juros R$:"));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(p4);
        
        JLabel lblttt = new JLabel("");
        p4.add(lblttt);

        
        contentPane.add(new JLabel(""));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton button = new JButton("Calcular");
        button.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
        			
        		int meses = Integer.parseInt(textField2.getText());
        		double deposito_mensal = Double.parseDouble(textField1.getText());
        		double juros = Double.parseDouble(textField3.getText());

        		Investimento inv = new Investimento(meses, juros, deposito_mensal);
   
        		String resultado = String.valueOf(inv.calculaTotal());
        		
        		lblttt.setText(resultado);
        	}
        });
        p5.add(button);
        contentPane.add(p5);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new CalculadoraInvestimentosGrid2().setVisible(true);
        });
    }
}