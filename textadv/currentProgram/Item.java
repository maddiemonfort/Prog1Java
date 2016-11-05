public class Item {
	//instance variables
	private String name;
	private String description;
	private boolean isTaken;
	private boolean isTangible;
	private boolean edible;
	private boolean attacks;

	//constructor
	public Item(String n, String d) {
		name = n;
		description = d;
		isTaken = false;
		if(n.equals("nector")) {
			isTangible = true;
			edible = true;
			attacks = false;
		}
		else if(n.equals("horn") || n.equals("sword")) {
			isTangible = true;
			edible = false;
			attacks = true;
		}
		else if(n.equals("painting") || n.equals("flower") || n.equals("hour-glass")) {
			isTangible = false;
			edible = false;
			attacks = false;
		}
		else {
			isTangible = true;
			edible = false;
			attacks = false;
		}
		
	}

	//methods
	public String getName() {
		return name;
	}

	public void pickUp() {
		isTaken = true;
	}

	public String getDescription() {
		return description;
	}
	public boolean tangible() {
		return isTangible;
	}

}