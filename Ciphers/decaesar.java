public class decaesar {
	public static void main(String[] args){
		String inputword = args[0];
		String inputkey = args[1];
		char[] code = inputword.toCharArray();
		int key = Integer.parseInt(inputkey);

		int length = code.length;
		int shifter = key % 26;

		for (int i = 0; i < length; i++) {
			int letter = (int)code[i];
			int intermediate = letter - shifter;
			 if(letter >= 65 && letter <= 90) { //uppercase
			 	if(intermediate >= 65) {
			 		char message = (char)intermediate;
			 		System.out.print(message);
			 	}
			 	else {
			 		int message = 91 - (shifter - (letter - 65));
			 		char origmessage = (char)message;
			 		System.out.print(origmessage);
			 	}
			 }
			 else if(letter >= 97 && letter <= 122) { //lowercase
			 	if(intermediate >= 97) {
			 		char message = (char)intermediate;
			 		System.out.print(message);
			 	}
			 	else {
			 		int message = 123 - (shifter -(letter - 97));
			 		char origmessage = (char)message;
			 		System.out.print(origmessage);
			 	}
			 }
			 else { //symbols
			 	char message = (char)letter;
			 	System.out.print(message);
			 }
		}
		System.out.println();
	}
}