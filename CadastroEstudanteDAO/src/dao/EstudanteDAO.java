package dao;
import modelo.Estudante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class EstudanteDAO {
	public List<Estudante> listar() throws SQLException {
		String sql = "SELECT id, nome, curso, nota FROM estudante ORDER BY nota DESC";
		List<Estudante> lista = new ArrayList<>();
		try (Connection con = Conexao.abrir();
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery()) {

		while (rs.next()) {
		lista.add(new Estudante(
		rs.getInt("id"),
		rs.getString("nome"),
		rs.getString("curso"),
		rs.getDouble("nota")));
		}
		}
		return lista;
		}
	public List<Estudante> buscarPorNome(String trecho) throws SQLException {
		String sql = "SELECT id, nome, curso, nota FROM estudante "
		+ "WHERE nome LIKE ? ORDER BY nota DESC";
		List<Estudante> lista = new ArrayList<>();
		try (Connection con = Conexao.abrir();
		PreparedStatement ps = con.prepareStatement(sql)) {
		ps.setString(1, "%" + trecho + "%");
		try (ResultSet rs = ps.executeQuery()) {
		while (rs.next()) {
		lista.add(new Estudante(
		rs.getInt("id"),
		rs.getString("nome"),
		rs.getString("curso"),
		rs.getDouble("nota")));
		}
		}
		}
		return lista;
		}
	public void inserir(Estudante e) throws SQLException {
		String sql = "INSERT INTO estudante (nome, curso, nota) VALUES (?, ?, ?)";
		try (Connection con = Conexao.abrir();
		PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		ps.setString(1, e.getNome());
		ps.setString(2, e.getCurso());
		ps.setDouble(3, e.getNota());
		ps.executeUpdate();

		try (ResultSet chaves = ps.getGeneratedKeys()) {
		if (chaves.next()) {
		e.setId(chaves.getInt(1));
		}
		}
		}
		}
	public boolean alterar(Estudante e) throws SQLException {
		String sql = "UPDATE estudante SET nome = ?, curso = ?, nota = ? WHERE id = ?";
		try (Connection con = Conexao.abrir();
		PreparedStatement ps = con.prepareStatement(sql)) {
		ps.setString(1, e.getNome());
		ps.setString(2, e.getCurso());
		ps.setDouble(3, e.getNota());
		ps.setInt(4, e.getId()); // o quarto ? e o do WHERE

		return ps.executeUpdate() > 0;

		}
		}
		public boolean excluir(int id) throws SQLException {
		String sql = "DELETE FROM estudante WHERE id = ?";
		try (Connection con = Conexao.abrir();
		PreparedStatement ps = con.prepareStatement(sql)) {
		ps.setInt(1, id);

		return ps.executeUpdate() > 0;

		}
		}
		
		
		
		}
