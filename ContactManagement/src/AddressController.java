import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressController {
	private Address model;
	private AddressView view;

	public AddressController(Address model, AddressView view) {
		super();
		this.model = model;
		this.view = view;
	}

	public void addAddress(int contactid, Contact contact) throws SQLException {

		model.addAddress(contactid, contact);
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

	public void display(ArrayList<Address> li) {
		Scanner sc = new Scanner(System.in);
		view.displayAddress(li);

	}
}
