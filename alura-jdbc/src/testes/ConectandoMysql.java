package testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectandoMysql {

	public static void main(String[] args) throws SQLException {
		
		// incluir connect para funcionar: deve ser um .jar adicionado externamente no build path
		
		Connection c = DriverManager.getConnection(	//faz a conexao com o banco
				"jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "963140");
		System.out.println("fechando conexao");
		c.close();
	}

}
