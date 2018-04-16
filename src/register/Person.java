package register;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * register.Person.
 */
public class Person implements Comparable<Person>,Serializable {
	/** Name of this person. */
	private String name;

	/** Phone number of this person. */
	private String phoneNumber;

	/**
	 * Construct a person.
	 * 
	 * @param name
	 *            name of the person
	 * @param phoneNumber
	 *            phone number of the person
	 */
	public Person(String name, String phoneNumber) {
		this.name = name;
		try {
			this.setPhoneNumber(phoneNumber);
		} catch (PhoneNumberFormatException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Returns name of this person.
	 * 
	 * @return name of this person
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of this person.
	 * 
	 * @param nameNew
	 *            name of this person
	 */
	public void setName(String nameNew) {
		name = nameNew;
	}

	/**
	 * Returns phone number of this person.
	 * 
	 * @return phone number of this person
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number of this person.
	 * 
	 * @param phoneNumberNew
	 *            phone number of this person
	 */
	public void setPhoneNumber(String phoneNumberNew) throws PhoneNumberFormatException {
		if (!isValidPhoneNumber(phoneNumberNew)) {
			throw new PhoneNumberFormatException("Wrong Number!");
		}
		phoneNumber = phoneNumberNew;
	}

	/**
	 * Validates the phone number. Valid phone numbers contains only digits.
	 * 
	 * @param phoneNumber
	 *            phone number to validate
	 * @return <code>true</code> if phone number is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean isValidPhoneNumber(String phoneNumber) {
		String pattern = "[\\d]{10}";
		Pattern pattern2 = Pattern.compile(pattern);
		Matcher matcher = pattern2.matcher(phoneNumber);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
//
	/**
	 * Returns a string representation of the person.
	 * 
	 * @return string representation of the person.
	 */
	public String toString() {
		return name + ", " + phoneNumber + ".";
	}

	@Override
	public int compareTo(Person o) {
		return this.getName().compareTo(o.getName());
	}
}
