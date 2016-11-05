import java.util.*;

public class GamePlay {
	public static void main(String[] args) {
		System.out.println("Welcome to The Labyrinth of Crete. Beware all ye who enter heere.");
		System.out.println("If you get stuck, try typing 'help'.");
		System.out.println();
		Scanner input = new Scanner(System.in);
		ProcessWords processor = new ProcessWords();
		String command = "";
		String printout = "";

		while(!(printout.equals("end"))) {
			command = input.nextLine();
			command.toLowerCase();
			printout = processor.processCommand(command);
			System.out.println(printout);
			System.out.println();
			//System.out.println("What do you want to do?");
			//System.out.println();
		}
	}

}