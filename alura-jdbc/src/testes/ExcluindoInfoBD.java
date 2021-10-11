package testes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ExcluindoInfoBD {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cf = new ConnectionFactory();
		Connection c = cf.conectarBD();
		Statement state = c.createStatement();
		boolean resultado = state.execute("DELETE FROM produto WHERE id>5;");
		int quantidadeModificacoes = state.getUpdateCount();
		System.out.println("RESULTADO > " + resultado);
		System.out.println("QUANTIDADE DE MODIFICACOES > " + quantidadeModificacoes);
	}
}
