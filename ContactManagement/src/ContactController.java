import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactController {
	private Contact model;
	private ContactView view;

	public ContactController(Contact model, ContactView view) {
		this.model = model;
		this.view = view;
	}

	public int addContact(Contact contact) throws SQLException {

		return model.addContact(contact);

	}

	public void editContact(int contactid, String field) throws SQLException {

		model.editContact(contactid, field);

	}

	public int getcid(String fname, String lname) throws SQLException {

		return model.getcid(fname, lname);
	}

	public void deleteContact(int contactid) throws SQLException {

		model.deleteContact(contactid);

	}

	public ArrayList<Contact> searchContact(String fname) throws SQLException {

		return model.searchContact(fname);

	}

	public void display(Contact c) {

		view.show(c);

	}

}
