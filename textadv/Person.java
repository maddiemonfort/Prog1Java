public class Person {
	//instance variables
	private String name;
	private String script;
	private int health;

	//constructor
	public Person(String n, String s, int h) {
		name = n;
		script = s;
		health = h;
	}

	//methods
	public String talk() {
		String words = name + ": " + script;
		return words;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public void lowerHealth(int h) {
		health = health - h;
	}
}