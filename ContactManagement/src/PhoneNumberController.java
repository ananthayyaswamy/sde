import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneNumberController {
	private PhoneNumber model;
	private PhoneNumberView phoneNumberView;
	public PhoneNumberController(PhoneNumber model, PhoneNumberView phoneNumberView) {
		super();
		this.model = model;
		this.phoneNumberView = phoneNumberView;
	}
	
	public void addPhone(int contactid,Contact contact) throws SQLException{

		model.addPhone(contactid, contact);
	}
public void editContact(int contactid ) throws SQLException{

		model.editContact(contactid);
		
	}

public int deleteContact(int contactid) throws SQLException{
	return model.deleteContact(contactid);

	
	
}
public ArrayList<Contact> SearchContact(ArrayList<Contact> li) throws SQLException{
return	model.SearchContact(li);

}
public void display(ArrayList<PhoneNumber> li){
	
	phoneNumberView.displayAddress(li);
}
}
