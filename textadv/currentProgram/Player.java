public class Player {
	//instance variables
	private String name;
	private int health;

	//constructor
	public Player() {
		name = "Theseus";
		health = 10;
	}

	//methods
	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int h) {
		health = h;
		System.out.println("Theseus: " + health);
	}
}