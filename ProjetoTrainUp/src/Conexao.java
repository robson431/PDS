import java.sql.*;
import java.util.Scanner;
public class Conexao {
public static void main(String[] args) throws SQLException {
	Scanner leia = new Scanner(System.in);
	
 String url = "jdbc:mysql://localhost:3306/loja";
 String user = "aluno_cd";
 String senha = "aluno_pw";
 
try (Connection con = DriverManager.getConnection(url, user, senha)) {
 System.out.println("Conectado a: " +
 con.getMetaData().getDatabaseProductName());
 
 String sqlIns = "INSERT INTO carro (nome,renavam,ano) VALUES (?,?,?)";
 try (PreparedStatement ps = con.prepareStatement(sqlIns)) {
	 
	System.out.println("Insira o Nome: ");
	String nome = leia.nextLine();
	
	System.out.println("Insira o renavam: ");
	String renavam = leia.nextLine();
	
	System.out.println("Insira o ano: ");
	int ano = leia.nextInt();
	
  ps.setString(1, nome);
  ps.setString(2, renavam);
  ps.setInt(3, ano);
  ps.executeUpdate();
  System.out.println("Usuario inserido.");
  

  String sqlSel = "SELECT * FROM carro ORDER BY ano DESC";
  try (Statement st = con.createStatement();
   ResultSet rs = st.executeQuery(sqlSel)) {
   System.out.println("\n--- Carros ---");
  while (rs.next()) {
   System.out.printf("%d | %s | %s | %d\n",
   rs.getInt("id"), rs.getString("nome"),
   rs.getString("renavam"), rs.getInt("ano"));
   }
   }
 }
 } catch (SQLException e) {
	 System.out.println("Erro de banco: " + e.getMessage());
 }

}
}
