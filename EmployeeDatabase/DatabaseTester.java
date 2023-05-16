public class DatabaseTester {

	public static void main(String[] args) {
		test00add();
		test01contains();
		test02remove();
	}

	public static void test00add() {
		/*
		 * make sure your default capacity is 11 before running this test!
		 */

		EmployeeDatabase set = new EmployeeDatabase();
		set.add(new Employee("Ron", 111111));
		System.out.println(set); //should show only Ron the array

		set.add(new Employee("Charlie", 222222));
		System.out.println(set); //should show Charlie and Ron in the array

		System.out.println(set.add(new Employee("Charlie", 222222))); //should print false

		for (char i = 97, j = 0; j < 10; i++, j++) { //should reject two entries
			set.add(new Employee("" + i, j));        //as the hash table is full
		}

		System.out.println(set); //should show a completely full array
		System.out.println(set.size());
	}

	public static void test01contains() {
		EmployeeDatabase set = new EmployeeDatabase();
		set.add(new Employee("Ron", 111111));
		System.out.println(set.contains(new Employee("Ron", 111111))); //should print true
		System.out.println(set.contains(new Employee("Ron", 222222))); //should print false
		System.out.println(set.contains(new Employee("R0n", 111111))); //should print false
	}

	public static void test02remove() {
		EmployeeDatabase set = new EmployeeDatabase();
		set.add(new Employee("Ron", 111111));
		System.out.println(set); //should show only Ron the array

		set.add(new Employee("Charlie", 222222));
		System.out.println(set); //should show Charlie and Ron in the array

		set.remove(new Employee("Ron", 111111));

		System.out.println(set); //should only show Charlie in the array

		System.out.println(set.remove(new Employee("Ron", 111111))); //should print false
	}
}
