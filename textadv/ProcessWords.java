public class ProcessWords {
	
	private GameState game;

	public ProcessWords() {
		game = new GameState();
	}

	public String processCommand(String command) {
		//parse the command into its words
		String[] words = parseCommand(command);

		//determine action to take based on words
		String action = determineAction(words);

		//return printout
		return action;
	}

	public String[] parseCommand(String command) {
		command = command.replace("to ","");
		command = command.replace("the ","");
		command = command.replace("with ","");
		return command.split(" ");
	}

	public String determineAction(String[] args) {

		if(args[0].equals("exit")) {
				return game.enterRoom();
		}

		else if(args[0].equals("look")) {
			if(game.getCurrentRoom().getName().equals("entrance")) {
				return "You are in the entrance room.";
			}
			else {
				return "You are in the Temple of " + game.getCurrentRoom().getName() + ".";
			}
		}

		else if(args[0].equals("examine") || args[0].equals("check")) {
			try {
				if(args[1].equals("scroll")) {
					return game.readScroll();
				}
				else if(args[1].equals("room") || args[1].equals(game.getCurrentRoom().getName())) {
					return game.look();
				}
				else if(args[1].equals(game.getItemName())) {
					return game.getItemDescription();
				}
				else if(args[1].equals("inventory")) {
					return game.getInventory();
				}
				else {
					return "there is no such thing here.";
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "examine what?";
			}
		}

		else if(args[0].equals("answer")) {
			try{
				if(args[1].equals(game.getRiddleAnswer())) {
					game.getCurrentRoom().setAnswered();
					return "Correct! You may proceed to the next room.";
				}
				if(args[1].equals("mellon")) {
					game.getCurrentRoom().setAnswered();
					return "Correct! You may proceed to the next room.";
				}
					
				else {
					return "Wrong!";
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "You need to input your answer.";
			}
			
		}

		else if(args[0].equals("talk")) {
			try {
				if(args[1].equals("Daedalus") || args[1].equals("man") || args[1].equals("daedalus")) {
					return game.talkDaedalus();
				}
				if(args[1].equalsIgnoreCase("Ariadne") || args[1].equals("princess") || args[1].equals("girl")) {
					return game.talkAriadne();
				}
				if(args[1].equalsIgnoreCase("Apollo")) {
					if(game.getCurrentRoom().getName().equals("apollo")) {
						return "The answer to the riddle is more than one word and the words are separated by '/' not spaces. As a hint, examine objects in the room. One other word of advice: in order to escape the labyrinth, a special object must be found.";
					}
					else {
						return "The god of prophecy and truth cannot aid you at this time.";
					}
				}
				if(args[1].equalsIgnoreCase("Minotaur")) {
					return game.talkMinotaur();
				}
				else {
					return "I think the labyrinth is getting to you. There is no such person here.";
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "talk to whom?";
			}
		}

		//if first word is take
		else if(args[0].equals("take")) {
			try {
				if(args[1].equals(game.getItemName())) {
					return game.takeItem(args[1]);
				}
				else {
					return "This item cannot be taken.";
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "take what?";
			}
		}

		else if(args[0].equals("drop")) {
			try {
				return game.dropItem(args[1]);
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "drop what?";
			}
		}

		else if(args[0].equals("use")) {
			try{
				if(args[1].equals("string")) {
					return game.useString();
				}
				if(args[1].equals("bag")) {
					return game.useBag();
				}
				else {
					return "That item cannot be used in this way.";
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "use what?";
			}
		}
		else if(args[0].equals("help")) {
			return "Try typing simple commands without articles or descriptive words. Also, try examining the room and things in the room for clues. Basic actions: examine, look, take, exit, answer.";
		}
		
		//if first word is attack
		else if(args[0].equals("attack")) {
			try {
				if(args[1].equals("sword") || args[1].equals("needle")) {
					return game.useSword();
				}
				else if(args[1].equals("horn")) {
					return game.useHorn();
				}
				else {
					return "You cannot attack with this.";
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "attack with what?";
			}
		}
		
		//if first word is eat or drink
		else if(args[0].equals("drink") || args[0].equals("eat")) {
			try {
				if(args[1].equals("nectar")) {
					return game.useNectar();
				}
				else {
					return "You cannot drink that.";
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "drink what?";
			}
		}

		//read Hobbit easter egg
		else if(args[0].equals("read")) {
			try {
				if(args[1].equals("book") || args[1].equalsIgnoreCase("The Hobbit")) {
					return game.readBook();
				}
				else {
					return "You cannot read that.";
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				return "read what?";
			}
		}

		else if(args[0].equals("quit")) {
			System.out.println("terminated program.");
			return "end";
		}

		else {
			 return "I dont understand that command.";	
		}	
	}
}