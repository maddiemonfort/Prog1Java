import java.util.*;
import java.io.*;

public class GameState {
	//instance variables
	private Room currentRoom;
	private List<Room> rooms;
	private boolean talkedToDaedalus;
	private Person daedalus;
	private boolean talkedToAriadne;
	private Person ariadne;
	private Inventory inventory;
	private boolean stringUsed;
	private boolean killedMinotaur;
	private Person minotaur;
	private Player player;
	private boolean isDead;

	//constructor
	public GameState() {
		currentRoom = new Room("entrance", "A stone room dimly lit by sparse torches on the wall. There is only one door and on the door is a scroll. Behind is a closed off door in the wall where you entered the labyrinth. A man is standing off to the side garbed in an ancient Greek chiton.", new Scroll("Speak friend and enter.", "mellon"), new Item("torch", "A blazing symbol of knowledge. Also useful to see by."));
		rooms = new ArrayList<Room>();
		addRooms(rooms);
		talkedToDaedalus = false;
		daedalus = new Person("Daedalus", "Greetings, wretched tribute from Athens. I am the creator of the labyrinth, and as such I have two words of advice for you. Firstly, to make your way through the maze, you must answer each riddle correctly. Secondly, I must warn you, once you leave a room, you can never go back. Now attend to your quest! Princess Ariadne awaits her savior from the beast within the labyrinth.", 1);
		ariadne = new Person("Ariadne", "Thank you, Theseus! Now take use that string to lead us back through the labyrinth, and let's get out of here!", 1);
		talkedToAriadne = false;
		inventory = new Inventory();
		stringUsed = false;
		killedMinotaur = false;
		minotaur = new Person("Minotaur", "Prepare to die, puny human.", 10);
		player = new Player();
		isDead = false;
	}

	//methods
	public String readScroll() {
		if(currentRoom.getName().equals("entrance")) {
			if(talkedToDaedalus) {
				return getCurrentRoom().getScroll().getRiddle();
			}
			else {
				return "There's a man in the room watching you. You should talk to him first.";
			}
		}
		else if(currentRoom.getName().equalsIgnoreCase("Ares")) {
			if(killedMinotaur) {
				return getCurrentRoom().getScroll().getRiddle();
			}
			else {
				return "There's something blocking your way to the scroll.";
			}
		}
		else {
			return getCurrentRoom().getScroll().getRiddle();
		}
		
	}

	public String getRiddleAnswer() {
			return currentRoom.getScroll().getAnswer();
	}

	public String enterRoom() { //or exit room
		if(currentRoom.getName().equals("Kronos")) {
			if(talkedToAriadne && stringUsed) {
				System.out.println("YOU WIN! Both you and Ariadne escape the maze and you return her to her family. After the exhuberant welcome, you and Ariadne travel to your home of Athens to bring the good news to your father...and the rest is mythology.");
				return "end";
			}
			else {
				return "Sorry, you may not leave this room yet, there is one more thing you must do.";
			}
		}
		else if(currentRoom.getName().equals("Ares")) {
			if(killedMinotaur && currentRoom.open()) {
				currentRoom = getNextRoom();
				return "entering the Temple of " + currentRoom.getName();
			}
			else {
				return "There is something blocking your way out.";
			}
		}
		else {
			if(currentRoom.open()) {
				currentRoom = getNextRoom();
				return "entering the Temple of " + currentRoom.getName();
			}
			else {
				return "The way is shut. You cannot get out.";
			}
		}
	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public Room getNextRoom() {
		ListIterator<Room> it = rooms.listIterator();
		currentRoom = it.next();
		it.remove();
		return currentRoom;
	}

	public String look() {
		return currentRoom.getDescription();
	}

	public String talkDaedalus() {
		talkedToDaedalus = true;
		return daedalus.talk();
	}

	public String talkAriadne() {
		if(currentRoom.getName().equals("Kronos")) {
			talkedToAriadne = true;
			return ariadne.talk();
		}
		else {
			return "There is no such person here.";
		}
	}

	public String talkMinotaur() {
		if(currentRoom.getName().equals("Ares")) {
			return minotaur.talk();
		}
		else {
			return "Thankfully, that beast is not here.";
		}
	}

	public String takeItem(String i) {
		if(currentRoom.getItem().tangible()) {
			return inventory.addItems(currentRoom.getItem());
		}
		else {
			return "You cannot take this item.";
		}
	}

	public String getInventory() {
		return inventory.getList();
	}

	public String useBag() {
		if(inventory.hasItem("bag")) {
			System.out.println("You have been blown out of the maze.");
			return "end";
		}
		else {
			return "You do not have a bag";
		}
	}

	public String getItemName() {
		return currentRoom.getItem().getName();
	}

	public String getItemDescription() {
		return currentRoom.getItem().getDescription();
	}

	public String useString() {
		if(inventory.hasItem("string")) {
			stringUsed = true;
			return "The power of the string enables you to escape the labyrinth.";
		}
		else {
			return "You do not have a string to use.";
		}
	}

	public String useHorn() {
		if(inventory.hasItem("horn") && currentRoom.getName().equals("Ares")) {
			killedMinotaur = true;
			return "You have killed the Minotaur!";
		}
		else {
			return "You may not attack at this time.";
		}
	}

	public String useSword() {
		int random = (int)(10*Math.random());
		if(inventory.hasItem("sword") && currentRoom.getName().equals("Ares")) {
			minotaur.lowerHealth(random);
			if(minotaur.getHealth() <= 0) {
				killedMinotaur = true;
				return "You have killed the Minotaur!";
			}
			else {
				player.setHealth(player.getHealth() - 2);
				if(player.getHealth() == 0) {
					System.out.println("You have been killed by the Minotaur!");
					System.out.println("GAME OVER");
					return "end";
				}
				else {
					return "You only wounded the Minotaur. The beast attacked you back.";
				}
			}
		}
		else {
			return "You may not attack at this time.";
		}
	}

	//healing or eating function


	public void addRooms(List<Room> r) {
		//read in the room names for the labyrinth
		try{
				BufferedReader rooms = new BufferedReader(new FileReader("Rooms.txt"));
				BufferedReader riddles = new BufferedReader(new FileReader("Riddles.txt"));
				BufferedReader answers = new BufferedReader(new FileReader("Answer.txt"));
				BufferedReader roomd = new BufferedReader(new FileReader("Descriptions.txt"));
				BufferedReader obj = new BufferedReader(new FileReader("Items.txt"));
				BufferedReader objd = new BufferedReader(new FileReader("ItemD.txt"));

				while(true) {
					String name = rooms.readLine();
					String riddle = riddles.readLine();
					String answer = answers.readLine();
					String description = roomd.readLine();
					String object = obj.readLine();
					String objectDescription = objd.readLine();
					if(name == null || riddle == null || answer == null) {
						break;
					}
					Scroll scroll = new Scroll(riddle, answer);
					Item item = new Item(object, objectDescription);
					r.add(new Room(name, description, scroll, item));
				}
			} catch(IOException ex) {
				System.out.println("ERROR: FILE NOT FOUND.");
			}
	}
}