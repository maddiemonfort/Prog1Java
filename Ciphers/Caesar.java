public class Caesar {
	public static void main(String[] args) {
		String phrase = args[0];
		char[] word = phrase.toCharArray();
		String number = args[1];
		int shifter = Integer.parseInt(number);
		int shift = shifter % 26;
		int numChars = word.length;
		
		for(int i = 0; i < numChars; i++) {
			int letter = (int)word[i];
			int intermediate = letter + shift;

			if(letter >= 65 && letter <=90) { //uppercase
				
				if(intermediate <= 90) {
					char newchar = (char)intermediate;
					System.out.print(newchar);
				}
				else {
					int newletter = shift - (90-letter) + 64;
					char newchar = (char)newletter;
					System.out.print(newchar);
				}
			}
			else if(letter >= 97 && letter <=122) { //lowercase
				
				if(intermediate <= 122) {
					char newchar = (char)intermediate;
					System.out.print(newchar);
				}
				else {
					int newletter = shift - (122-letter) + 96;
					char newchar = (char)newletter;
					System.out.print(newchar);
				}
			}
			else { //symbols and spaces
				char newchar = (char)letter;
				System.out.print(newchar);
			}
		
			
		}
		System.out.println();
	}
}		