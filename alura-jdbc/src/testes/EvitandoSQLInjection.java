package testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EvitandoSQLInjection {
	public static void main(String[] args) throws SQLException {
		String nome = "tester";
		String descricao = "colher, garfo, 'faca";

		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.conectarBD();
		 c.setAutoCommit(false); // -> caso esteja DESCOMENTADO, o programa ir� alterar o bd. comentado, n�o � poss�vel 
		//inserir automaticamente as altera��es no banco (por�m, o programa roda)

		 
		// PreparedStatement > evita sql injection, j� que entende qualquer informa��o como string
		PreparedStatement ps = c.prepareStatement("INSERT INTO produto(nome, descricao) values (?, ?);",
				Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, nome);
		ps.setString(2, descricao);
		ps.execute();

		c.commit();	//usa-se em conjunto com o setAutoCommit(false). caso o script rode certo, deve-se usar o commit(), caso
		c.rollback(); //rode errado, deve-se usar o rollback();
		
		ResultSet rst = ps.getGeneratedKeys();
		while (rst.next()) {
			Integer idProduto = rst.getInt(1);
			System.out.println("ID PRODUTO INSERIDO (KEY) > " + idProduto);
		}
		c.close();
	}
}
