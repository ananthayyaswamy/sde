import java.security.SecureRandom;
import java.util.Random;

public class codeGen {
	public static String generateRandom() {
		String aToZ = "ABCDefghijklmnopqrstuvwxyz23456"; // 36 letter.

		Random rand = new Random();
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			int randIndex = rand.nextInt(aToZ.length());
			res.append(aToZ.charAt(randIndex));
		}
		 System.out.println(" generated token "+res.toString().toUpperCase());
		return res.toString().toUpperCase();
	}
// public static void main(String args[]){
//	 System.out.println("String:"+generateRandom());
//	 System.out.println(System.currentTimeMillis()/1000/30);
//	
// }
}