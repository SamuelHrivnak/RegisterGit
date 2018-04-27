package register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC {
	private static final String URL = "jdbc:postgresql://localhost/test";
	private static final String USER = "postgres";
	private static final String PASSWORD = "mastic1994";

	private static final String INSERT = "INSERT INTO student(ident, firstname, lastname, age) VALUES ('1','Janko','Hrasko','20')";
	private static final String INSERT2 = "INSERT INTO student(ident, firstname, lastname, age) VALUES (?,?,?,?)";
	private static final String INSERT3 = "INSERT INTO student(ident, firstname, lastname, age) VALUES ('3','Ferko','Gloc_k','35')";
	private static final String SELECT = "SELECT ident, firstname, lastname, age FROM student";

	public static void main(String[] args) throws Exception {
		 try(Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);
		 Statement statement = connection.createStatement()){
		 statement.executeUpdate(INSERT);
		 statement.executeUpdate(INSERT2);
		 statement.executeUpdate(INSERT3);
		
		 }

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(SELECT)) {
			while (rs.next()) {
				System.out.printf("%d %s %s %d\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		}

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD); // puzivame otazniky vo VALUES,
																						// cisla v param je poradie
																						// insertov
				PreparedStatement statement = connection.prepareStatement(INSERT2)) {
			statement.setInt(1, 10);
			statement.setString(2, "Katka");
			statement.setString(3, "Kratka");
			statement.setInt(4, 30);
			statement.executeUpdate();

		}
	}

}
