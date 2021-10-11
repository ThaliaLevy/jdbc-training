package testes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InserindoInfoBD {
	public static void main(String[] args) throws SQLException {

		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.conectarBD();

		Statement state = c.createStatement();
		boolean resultado = state.execute("INSERT INTO produto(nome, descricao) values ('mouse', 'mouse sem fio');",
				Statement.RETURN_GENERATED_KEYS); // generated keys retorna o id criado com a inserção recente

		System.out.println("RESULTADO BOOLEAN: " + resultado);

		ResultSet rst = state.getGeneratedKeys();
		while (rst.next()) {
			Integer idProduto = rst.getInt(1);
			System.out.println("ID PRODUTO INSERIDO (KEY) > " + idProduto);
		}

		c.close();
	}
}
