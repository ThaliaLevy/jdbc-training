package testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListandoInfoBD {

	public static void main(String[] args) throws SQLException {
		
		Connection c = DriverManager.getConnection(	//faz a conexao com o banco
				"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "963140");
		
		Statement state = c.createStatement();	//classe que possibilita a intera��o com o bd. retorna booleano
		boolean resultado = state.execute("SELECT * FROM PRODUTO;");
		System.out.println("RESULTADO BOOLEAN: " + resultado);	
		
		ResultSet rst = state.getResultSet();	//pega as informa��es do bd em formato de objeto
		
		//informa��es pegas atrav�s dos nomes das colunas
		while(rst.next()) {
			Integer idProduto = rst.getInt("id");
			String nomeProduto = rst.getString("nome");
			String descricaoProduto = rst.getString("descricao");
			
			System.out.println("ID PRODUTO > " + idProduto + "\nNOME PRODUTO > " + nomeProduto + "\nDESCRI��O PRODUTO > " + descricaoProduto);
		}
		
		c.close();
	}

}

