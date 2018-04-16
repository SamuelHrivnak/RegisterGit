package register;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		Register register = new ArrayRegister();
//
//		register.addPerson(new Person("Janko Hrasko", "0900123450"));
//		register.addPerson(new Person("Samuel Hrivnak", "0919358004"));
//		register.addPerson(new Person("Milada Havirova", "0902658985"));
		register.loadData();
		//register.saveData();
		ConsoleUI ui = new ConsoleUI(register);
		ui.run();
	}
}
