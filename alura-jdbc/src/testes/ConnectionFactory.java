package testes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Factory Methods: é um design patterns que busca centralizar metodos em classes
//nesse caso, está centralizando a ação de conectar com o bd
public class ConnectionFactory {

	public Connection conectarBD() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC",
				"root", "963140");
	}
}
