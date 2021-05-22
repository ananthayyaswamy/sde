import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailAddressController {
	private EmailAddress model;
	private EmailAddressView view;

	public EmailAddressController(EmailAddress model, EmailAddressView view) {
		super();
		this.model = model;
		this.view = view;
	}

	public void addEmailAddress(int contactid, Contact contact) throws SQLException {
		
		model.addEmailAddress(contactid, contact);
	}

	public void editContact(int contactid) throws SQLException {
		
model.editContact(contactid);
	}

	public void deleteContact(int contactid) throws SQLException {
		
		model.deleteContact(contactid);

	}

	public ArrayList<Contact> SearchContact(ArrayList<Contact> li) throws SQLException {

		
		return model.SearchContact(li);
	}

	public void display(ArrayList<EmailAddress> li) {

		view.displayAddress(li);
	}
}
