package register;

public class Tester {

	public static void main(String[] args) {
		JDBCRegister jdbc = new JDBCRegister();
	//	jdbc.addPerson(new Person("Samko", "0918986542"));
	//	jdbc.removePerson(3);
	//	System.out.println(jdbc.findPersonByPhoneNumber("0919358659").toString());
		System.out.println(jdbc.getSize());
		//jdbc.printFromDatabase();
		System.out.println(jdbc.getPersonByRow(1).toString());

	}

}
