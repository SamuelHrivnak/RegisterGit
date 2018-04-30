package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import register.Person;
import register.Register;

public class DatabaseRegister implements Register {
	/** register.Person array. */
	private List<Person> persons = new ArrayList<>();
	private static final String URL = "jdbc:postgresql://localhost/register_database";
	private static final String USER = "postgres";
	private static final String PASSWORD = "mastic1994";
	
	public DatabaseRegister() {
		// persons = new Person[size];
		// count = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getCount()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getSize()
	 */
	@Override
	public int getSize() {
		return persons.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons.get(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) {
		if (findPersonByName(person.getName()) != null)
			throw new RuntimeException("Person with the same name already exists!");
		else if (findPersonByPhoneNumber(person.getPhoneNumber()) != null)
			throw new RuntimeException("Person with the same phone number already exists!");
		else {
			persons.add(person);
			saveData();
			Collections.sort(persons);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {
		//return persons.stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
		
		
		for (int index = 0; index < persons.size(); index++) {
			if (persons.get(index).getName().equals(name)) {
				return persons.get(index);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (int index = 0; index < persons.size(); index++) {
			if (persons.get(index).getPhoneNumber().equals(phoneNumber)) {
				return persons.get(index);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(int index) {
		//TODO
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			Person s = iterator.next(); // must be called before you can call i.remove()
			// Do something
			if (s.equals(index)) {
				iterator.remove();
				saveData();
			}
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
		for (int index = 0; index < persons.size(); index++) {
			if (persons.get(index).equals(person)) {
				return index;
			}
		}
		return -1;
	}
	public void deleteAllBy(char firstLetter) {
//		for (Person person : persons) {
//			if (person.getName().charAt(0) == firstLetter) {
//				removePerson(person);
//			}
//		}
	}
	public List<Person> findAllPersonByNameContains(String nameContains){
		List<Person> returnArray = new ArrayList<>();
		for (Person person : persons) {
			if (person.getName().contains(nameContains)) {
				returnArray.add(person);
			}
		}		
		return returnArray;	
	}

	@Override
	public void loadData() {
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement statement = connection.prepareStatement("SELECT name, number FROM register.persons");
				ResultSet rs = statement.executeQuery()){			
				while(rs.next()) {		
					persons.add(new Person(rs.getString(1), rs.getString(2)));
				}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void saveData() {
		String DROP_STATEMENT = "DROP TABLE register.persons";
		String INSERT_INTO_STATEMENT = "INSERT INTO register.persons (name, number) VALUES (? , ?)";
		String CREATE_TABLE_STATEMENT = "CREATE TABLE register.persons (name varchar(64), number varchar(64))";
		
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement statement = connection.prepareStatement(INSERT_INTO_STATEMENT);
			Statement statement_update = connection.createStatement();	
					){
		
			statement_update.executeUpdate(DROP_STATEMENT);
			statement_update.executeUpdate(CREATE_TABLE_STATEMENT);
			
			
			for (Person person : persons) {
				statement.setString(1,person.getName());
				statement.setString(2, person.getPhoneNumber());	
				statement.executeUpdate();
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Person getPersonByRow(int row) {
		// TODO Auto-generated method stub
		return null;
	}
}
