
public class runner {
	public static void main(String[] args) {
		int count = 0;
		for (int j =5; j < 10; j++) {
			if(j%2 !=1) {
				count = j + count;
				
			}System.out.println("j = "+ j+ ", count = "+ count);
		}
		System.out.println(count);
	}
}
