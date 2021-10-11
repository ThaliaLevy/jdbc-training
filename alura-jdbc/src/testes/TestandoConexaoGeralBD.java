package testes;

import java.sql.Connection;
import java.sql.SQLException;

public class TestandoConexaoGeralBD {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory cg = new ConnectionFactory();
		Connection  c = cg.conectarBD();	//chamando a função de conexao com o BD, da classe conexaoGeral
		
		System.out.println("Fechando conexão");
		
		c.close();

	}

}
