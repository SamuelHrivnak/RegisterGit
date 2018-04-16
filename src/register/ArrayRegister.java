package register;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
	/** register.Person array. */
	// private Person[] persons;
	List<Person> persons = new ArrayList<>();
	private final static String file = "out.bin";

	/** Number of persons in this register. */
	// private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister() {
		// persons = new Person[size];
		// count = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return persons.size();
	}

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
	public void removePerson(Person person) {
		Iterator<Person> iterator = persons.iterator();
		while (iterator.hasNext()) {
			Person s = iterator.next(); // must be called before you can call i.remove()
			// Do something
			if (s.equals(person)) {
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
		for (Person person : persons) {
			if (person.getName().charAt(0) == firstLetter) {
				removePerson(person);
			}
		}
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
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			persons = (List<Person>) ois.readObject();
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void saveData() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {			
				oos.writeObject(persons);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
