package visao;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.EstudanteDAO;
import modelo.Estudante;

public class JanelaEstudante extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCurso;
	private JTextField txtNota;
	private JTextField txtBusca;
	private JTable tabela;
	private JLabel lblStatus;

	private DefaultTableModel modelo;
	private final EstudanteDAO dao = new EstudanteDAO();
	private int idSelecionado = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaEstudante frame = new JanelaEstudante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JanelaEstudante() {

		setTitle("Cadastro de Estudante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 550);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNome.setBounds(30, 30, 70, 25);
		contentPane.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(100, 30, 250, 25);
		contentPane.add(txtNome);

		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurso.setBounds(30, 70, 70, 25);
		contentPane.add(lblCurso);

		txtCurso = new JTextField();
		txtCurso.setBounds(100, 70, 250, 25);
		contentPane.add(txtCurso);

		JLabel lblNota = new JLabel("Nota:");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNota.setBounds(30, 110, 70, 25);
		contentPane.add(lblNota);

		txtNota = new JTextField();
		txtNota.setBounds(100, 112, 100, 25);
		contentPane.add(txtNota);

		JLabel lblBuscar = new JLabel("Buscar:");
		lblBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBuscar.setBounds(30, 150, 70, 25);
		contentPane.add(lblBuscar);

		txtBusca = new JTextField();
		txtBusca.setBounds(100, 150, 250, 25);
		contentPane.add(txtBusca);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btnCadastrar.setBounds(30, 195, 110, 30);
		contentPane.add(btnCadastrar);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterar();
			}
		});
		btnAlterar.setBounds(150, 195, 100, 30);
		contentPane.add(btnAlterar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(260, 195, 100, 30);
		contentPane.add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(370, 195, 100, 30);
		contentPane.add(btnLimpar);

		JButton btnListar = new JButton("Listar todos");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setText("");
				listar();
			}
		});
		btnListar.setBounds(480, 195, 120, 30);
		contentPane.add(btnListar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(610, 195, 100, 30);
		contentPane.add(btnBuscar);

		tabela = new JTable();

		modelo = new DefaultTableModel(new String[] { "ID", "Nome", "Curso", "Nota" }, 0);

		tabela.setModel(modelo);
		tabela.setRowHeight(22);
		tabela.setDefaultEditor(Object.class, null);

		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					carregarSelecionado();
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(30, 254, 680, 190);
		contentPane.add(scrollPane);

		lblStatus = new JLabel("Status:");
		lblStatus.setBounds(30, 455, 100, 25);
		contentPane.add(lblStatus);

		listar();
	}

	private void listar() {
		try {
			preencherTabela(dao.listar());
		} catch (SQLException ex) {
			erro("Erro ao listar", ex);
		}
	}

	private void buscar() {
		try {
			preencherTabela(dao.buscarPorNome(txtBusca.getText().trim()));
		} catch (SQLException ex) {
			erro("Erro ao buscar", ex);
		}
	}

	private void alterar() {
		if (idSelecionado == 0) {
			JOptionPane.showMessageDialog(this, "Selecione primeiro uma linha da tabela.",

					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		Estudante e = lerFormulario();
		if (e == null)
			return;
		// O id vem da SELECAO, nao do que esta digitado.
		e.setId(idSelecionado);
		try {
			dao.alterar(e);
			JOptionPane.showMessageDialog(this, "Estudante alterado.");
			limpar();
			listar();
		} catch (SQLException ex) {
			erro("Erro ao alterar", ex);
		}
	}

	private void excluir() {
		if (idSelecionado == 0) {
			JOptionPane.showMessageDialog(this, "Selecione primeiro uma linha da tabela.",

					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;
		}
		int opcao = JOptionPane.showConfirmDialog(this, "Excluir o estudante " + txtNome.getText() + "?", "Confirmacao",
				JOptionPane.YES_NO_OPTION);
		if (opcao != JOptionPane.YES_OPTION)
			return;
		try {
			dao.excluir(idSelecionado);
			JOptionPane.showMessageDialog(this, "Estudante excluido.");
			limpar();
			listar();
		} catch (SQLException ex) {
			erro("Erro ao excluir", ex);
		}
	}

	private void preencherTabela(List<Estudante> lista) {
		modelo.setRowCount(0);

		for (Estudante e : lista) {
			modelo.addRow(new Object[] { e.getId(), e.getNome(), e.getCurso(), e.getNota() });
		}

		lblStatus.setText(lista.size() + " estudante(s) na tabela.");
	}

	private Estudante lerFormulario() {
		String nome = txtNome.getText().trim();
		String curso = txtCurso.getText().trim();

		if (nome.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Preencha o nome!", "Aviso", JOptionPane.WARNING_MESSAGE);

			txtNome.requestFocus();

			return null;
		}

		double nota;

		try {
			nota = Double.parseDouble(txtNota.getText().trim().replace(",", "."));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Nota deve ser um numero!", "Aviso", JOptionPane.WARNING_MESSAGE);

			txtNota.requestFocus();

			return null;
		}

		if (nota < 0 || nota > 10) {
			JOptionPane.showMessageDialog(this, "A nota deve estar entre 0 e 10.", "Aviso",
					JOptionPane.WARNING_MESSAGE);

			txtNota.requestFocus();

			return null;
		}

		return new Estudante(nome, curso, nota);
	}

	private void cadastrar() {
		Estudante e = lerFormulario();

		if (e == null) {
			return;
		}

		try {
			dao.inserir(e);

			JOptionPane.showMessageDialog(this, "Estudante cadastrado com o id " + e.getId() + ".");

			limpar();
			listar();

		} catch (SQLException ex) {
			erro("Erro ao cadastrar", ex);
		}
	}

	private void limpar() {
		idSelecionado = 0;
		txtNome.setText("");
		txtCurso.setText("");
		txtNota.setText("");
		tabela.clearSelection();
		txtNome.requestFocus();
		lblStatus.setText("Formulario limpo.");
	}

	private void erro(String contexto, SQLException ex) {
		JOptionPane.showMessageDialog(this, contexto + ": " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

		lblStatus.setText(contexto + ".");
	}

	private void carregarSelecionado() {
		int linha = tabela.getSelectedRow();

		if (linha < 0) {
			return;
		}

		idSelecionado = (int) modelo.getValueAt(linha, 0);

		txtNome.setText(String.valueOf(modelo.getValueAt(linha, 1)));
		txtCurso.setText(String.valueOf(modelo.getValueAt(linha, 2)));
		txtNota.setText(String.valueOf(modelo.getValueAt(linha, 3)));

		lblStatus.setText("Editando o estudante de id " + idSelecionado + ". Altere os campos e clique em Alterar.");
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public JTextField getTxtCurso() {
		return txtCurso;
	}

	public JTextField getTxtNota() {
		return txtNota;
	}

	public JTextField getTxtBusca() {
		return txtBusca;
	}

	public JTable getTabela() {
		return tabela;
	}

	public JLabel getLblStatus() {
		return lblStatus;
	}
}
