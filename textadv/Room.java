public class Room {
	//instance variables
	private String name;
	private String description;
	private boolean isOpen;
	private Scroll scroll;
	private Item item;

	//constructor
	public Room(String n, String d, Scroll s, Item i) {
		name = n;
		description = d;
		isOpen = false;
		scroll = s;
		item = i;
	}

	//methods
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	public boolean open() {
		return isOpen;
	}

	public Scroll getScroll() {
		return scroll;
	}

	public void setAnswered() {
		isOpen = true;
	}

	public Item getItem() {
		return item;
	}
	
}

