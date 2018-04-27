package register;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

/**
 * User interface of the application.
 */
public class ConsoleUI {
	/** register.Register of persons. */
	private Register register;

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};
	
	private enum FindOption{
		FIND_BY_NAME, FIND_BY_NUMBER
	};

	public ConsoleUI(Register register) {
		this.register = register;
	}

	public void run() {
		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:
				return;
			}
		}
	}

	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}

	public void printRegister() {
		for (int i = 1; i <= register.getSize(); i++) {
			System.out.println("[ "+register.getPersonByRow(i).toString()+ " ]");
		}
		
	}

	private void addToRegister() {
		System.out.println("Enter Name: ");
		String name = readLine();
		System.out.println("Enter Phone Number: ");
		String phoneNumber = readLine();

		try {
			register.addPerson(new Person(name, phoneNumber));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	private void updateRegister() {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		System.out.println(index + ". " + person.toString());
		
		System.out.print("New name: ");
		String name = readLine();
		person.setName(name);
		
		System.out.print("New phone number: ");
		String phoneNumber = readLine();
		try {
			person.setPhoneNumber(phoneNumber);
		} catch (PhoneNumberFormatException e) {
			System.err.println(e.getMessage());
		}
		
		System.out.println(index + ". " + person.toString());
		
	}

	private void findInRegister() {
		System.out.println("Find person by 1) NAME, 2)TEL.NUMBER \n Type number of choice");
		String choice = readLine();	
		
		if (choice.equals("1")) {
			System.out.print("Name: ");
			String name = readLine();	
			if (register.findPersonByName(name) !=null) {
				System.out.println("Persons telephone number with this name is: " + register.findPersonByName(name).getName());
			}else {
				System.out.println("Person with that name is not in our database");
			}
			
		}
		if (choice.equals("2")) {
			System.out.print("Telephone ");
			String telephone = readLine();	
			if (register.findPersonByPhoneNumber(telephone) !=null) {
				System.out.println("Persons name with this telephone number is: " + register.findPersonByPhoneNumber(telephone).getName());
			}else {
				System.out.println("Person with that name is not in our database");
			}
			
		}		
	}
	
	private void removeFromRegister() {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		register.removePerson(index);
	}

}
