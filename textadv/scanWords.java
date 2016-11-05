import java.util.*;


public class scanWords {
	
	public static void main(String[] args) {
		System.out.println("Commands: ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		input = input.replace("Let's ","");
		input = input.replace("the ","");
		input = input.replace("I'm","You're");
		input = input.replace("go","going");
		input = input.replace("examine","examining");
		System.out.println(input);
		System.out.println("A box without hinges, key, or lid. Yet golden treasure inside is hid. What am I?");
		String in = sc.nextLine();
		if(in.equalsIgnoreCase("egg")) {
			System.out.println("Correct! Proceed to the next room.");
		}
		else{
			System.out.println("Wrong. You cannot get out.");
		}
	}
	
}