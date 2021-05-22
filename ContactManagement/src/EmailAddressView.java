import java.util.ArrayList;

public class EmailAddressView {
	public void displayAddress(ArrayList<EmailAddress> li){
		for(EmailAddress address:li){
			System.out.println("EmailAddress type:"+address.getType());
			System.out.println(" Email Address :"+address.getEmailAddress());
		}
		
	}
}
