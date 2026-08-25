package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/escola";
	private static final String USER = "aluno_cd";
	private static final String SENHA = "aluno_pw";

// Abre uma conexao NOVA. Quem chamou e responsavel por fechar.
	public static Connection abrir() throws SQLException {
		return DriverManager.getConnection(URL, USER, SENHA);
	}

// Teste de ambiente: rode esta classe quando quiser saber se o
// problema esta na sua maquina e nao no seu codigo.
	public static void main(String[] args) {
		try (Connection con = abrir()) {
			System.out.println("Conexao estabelecida.");
			System.out.println("Servidor : " + con.getMetaData().getDatabaseProductName());
			System.out.println("Banco : " + con.getCatalog());
		} catch (SQLException e) {
			System.out.println("Falha na conexao: " + e.getMessage());
		}
	}
}