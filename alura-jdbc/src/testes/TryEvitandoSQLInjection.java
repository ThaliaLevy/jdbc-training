package testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//com o try-with-resources (try-catch) n�o � necess�rio fechar as classes, j� que ele  executa o m�todo close sem que precise 
//estar expl�cito no c�digo

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
