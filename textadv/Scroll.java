public class Scroll {
	//instance variables
	private String riddle;
	private String answer;

	//constructor
	public Scroll(String r, String a) {
		riddle = r;
		answer = a;
	}

	//methods
	public String getRiddle() {
		return riddle;
	}

	public String getAnswer() {
		return answer;
	}

	public void setRiddle(String newR) {
		riddle = newR;
	}
}