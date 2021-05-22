import java.util.ArrayList;

public class PhoneNumberView {
	public void displayAddress(ArrayList<PhoneNumber> li) {
		for (PhoneNumber address : li) {
			System.out.println("Phone Number type:" + address.getType());
			System.out.println("Phone Number :" + address.getPhoneNumber());
		}

	}
}
