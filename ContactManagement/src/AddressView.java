import java.util.ArrayList;

public class AddressView {
public void displayAddress(ArrayList<Address> li){
	for(Address address:li){
		System.out.println("Address type:"+address.getType());
		System.out.println("Address :"+address.getAddress());
	}
	
}
}
