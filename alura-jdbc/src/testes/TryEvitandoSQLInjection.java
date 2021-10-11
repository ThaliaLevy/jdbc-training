package testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//com o try-with-resources (try-catch) não é necessário fechar as classes, já que ele  executa o método close sem que precise 
//estar explícito no código

public class TryEvitandoSQLInjection {
	public static void main(String[] args) throws SQLException {
		String nome = "tester";
		String descricao = "colher, garfo, 'faca";

		ConnectionFactory cf = new ConnectionFactory();
		try (Connection c = cf.conectarBD()) {

			c.setAutoCommit(false);

			try (PreparedStatement ps = c.prepareStatement("INSERT INTO produto(nome, descricao) values (?, ?);",
					Statement.RETURN_GENERATED_KEYS)) {

				ps.setString(1, nome);
				ps.setString(2, descricao);
				ps.execute();
				c.commit();

				ResultSet rst = ps.getGeneratedKeys();
				while (rst.next()) {
					Integer idProduto = rst.getInt(1);
					System.out.println("ID PRODUTO INSERIDO (KEY) > " + idProduto);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("rollback!");
				c.rollback();
			}
		}
	}
}
