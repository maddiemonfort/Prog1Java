import java.util.*;

public class Inventory {
	//instance variables
	private List<String> items;

	//constructor
	public Inventory() {
		items = new ArrayList<String>();
	}

	//methods
	public String addItems(Item i) {
		if(hasItem(i.getName())) {
			return "You already have this item.";
		}
		else {
			String item = i.getName();
			items.add(item);
			return item + " taken.";
		}
	}

	public String getList() {
		return "Inventory: " + items.toString();
	}

	public boolean hasItem(String i) {
		boolean haveItem = false;
		Iterator it = items.iterator();
		while(it.hasNext()) {
			if(it.next().equals(i)) {
				haveItem = true;
			}
		}
		return haveItem;
	}
}