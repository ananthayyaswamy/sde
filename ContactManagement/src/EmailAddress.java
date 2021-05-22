import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailAddress {
	private String emailAddress;

	public EmailAddress(String emailAddress, String type) {
		super();
		this.emailAddress = emailAddress;
		this.type = type;
	}

	public EmailAddress() {
		// TODO Auto-generated constructor stub
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String type;

	public void addEmailAddress(int contactid, Contact contact) throws SQLException {
		Database db = new Database();
		db.addEmailIntoDatabase(contact, contactid);
	}

	public void editContact(int contactid) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Database d = new Database();
		
		System.out.println("enter the type of Email you want to edit");
		String type = sc.nextLine();
		System.out.println("Enter the new value");
		String value = sc.nextLine();
		d.editTypeContact(contactid, "emailaddress", value, "email", type);

	}

	public void deleteContact(int contactid) throws SQLException {
		Database d = new Database();

		d.deleteContact(contactid, "email");

	}

	public ArrayList<Contact> SearchContact(ArrayList<Contact> li) throws SQLException {
		Database d = new Database();
		for (Contact contact : li) {
			int contactid = d.getContactId(contact.getFirstName());
			contact = d.searchEmail(contact, contactid, "email");
		}
		return li;
	}
}
