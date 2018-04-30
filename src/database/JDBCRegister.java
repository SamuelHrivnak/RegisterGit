package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import register.Person;
import register.Register;

public class JDBCRegister implements Register {
	private static final String URL = "jdbc:postgresql://localhost/register_database";
	private static final String USER = "postgres";
	private static final String PASSWORD = "mastic1994";

	@Override
	public int getSize() {
		final String INSERT = "SELECT count(id) FROM register.persons";
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.err.println("ziadna dlzka");
		return -1;
	}

	
	@Override
	public Person getPerson(int index) {
		final String INSERT = "SELECT name, number FROM register.persons WHERE index =?";
		Person person = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setInt(1, index);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				person = new Person(rs.getString(1), rs.getString(2));
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("osoba sa nenašla");
		return person;
	}
	
	public Person getPersonId(int index) {
		final String INSERT = "SELECT name, number FROM register.persons WHERE index =?";
		Person person = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setInt(1, index);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				person = new Person(rs.getString(1), rs.getString(2));
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("osoba sa nenašla");
		return person;
	}

	public Person getPersonByRow(int row) {

		String INSERT = "SELECT name, number FROM register.persons ORDER BY id LIMIT 1 OFFSET ?";
		Person person = null;

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setInt(1, row - 1);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				person = new Person(rs.getString(1), rs.getString(2));
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("osoba sa nenašla");
		return person;
	}
	
	public void updatePerson(String sMeno,String nMeno, String n ) {
		//TODO
	}


	@Override
	public void addPerson(Person person) {
		final String INSERT = "INSERT INTO register.persons(name,number) VALUES (?,?)";
		// if (findPersonByName(person.getName()) != null)
		// throw new RuntimeException("Person with the same name already exists!");
		// else if (findPersonByPhoneNumber(person.getPhoneNumber()) != null)
		// throw new RuntimeException("Person with the same phone number already
		// exists!");
		// else {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setString(1, person.getName());
			statement.setString(2, person.getPhoneNumber());
			statement.executeUpdate();

		} catch (SQLException e) {
			System.err.println("addperson chyba");
			e.printStackTrace();
		}
	}
	// }


	@Override
	public Person findPersonByName(String name) {
		final String INSERT = "SELECT name, number FROM register.persons WHERE name =?";
		Person person = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				person = new Person(rs.getString(1), rs.getString(2));
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("osoba sa nenašla");

		return null;
	}


	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		final String INSERT = "SELECT name, number FROM register.persons WHERE number =?";
		Person person = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setString(1, phoneNumber);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				person = new Person(rs.getString(1), rs.getString(2));
				return person;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("osoba sa nenašla");
		return null;
	}

	


@Override
	public void removePerson(int index) {
		final String INSERT = "DELETE FROM register.persons WHERE id =?";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			statement.setInt(1, index);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void printFromDatabase() {
		final String INSERT = "SELECT * FROM register.persons ";
		Person person = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				person = new Person(rs.getString(1), rs.getString(2));
				System.out.println("[ " + person.toString() + " ]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	

	/**
	 * Returns index of a specified person from register
	 * 
	 * @param person
	 *            person to find
	 * @return index of a person, returns -1 if not found
	 */
	private int findIndexOfPerson(Person person) {
		return -1;
	}

	public void deleteAllBy(char firstLetter) {
		// for (Person person : persons) {
		// if (person.getName().charAt(0) == firstLetter) {
		// removePerson(person);
		// }
		// }
	}

	public List<Person> findAllPersonByNameContains(String nameContains) {

		return null;
	}

	@Override
	public void loadData() {

	}

	@Override
	public void saveData() {

	}

}
