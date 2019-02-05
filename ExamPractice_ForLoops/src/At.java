
public class At extends Yo {
	private int myZ =0;
	public At() {
		super();
		myZ = 100;
	}
	public String toString() {
		return myZ + " " + super.toString();
	}
}
