import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Contact {
	private String firstName;
	private String lastName;
	private String company;
	private String dob;
	public int getContactid() {
		return contactid;
	}

	public void setContactid(int contactid) {
		this.contactid = contactid;
	}
	private ArrayList<PhoneNumber> phoneNumber;
	private ArrayList<EmailAddress> emailAddress;
	private ArrayList<Address> address;
	private int contactid;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ArrayList<PhoneNumber> getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(ArrayList<PhoneNumber> phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<EmailAddress> getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(ArrayList<EmailAddress> emailAddress) {
		this.emailAddress = emailAddress;
	}

	public ArrayList<Address> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCompany() {
		return company;
	}

	public String getDob() {
		return dob;
	}
public int addContact(Contact contact) throws SQLException{
		
		Database d=new Database();
		int contactid=d.addContactIntoDatabase(contact);
		
		return contactid;
		
	}
	public void editContact(int contactid,String field ) throws SQLException{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the new value");
		String value=sc.nextLine();
		Database d=new Database();
		//int contactid=d.getContactId(fname, lastname);
		d.editContact(contactid, field, value,"contact");
		
		
		
	}
	public int getcid(String fname,String lname) throws SQLException{
		Database d=new Database();
		int contactid=d.getContactId(fname, lname);
		return contactid;
	}
	public void deleteContact(int contactid) throws SQLException{
		Database d=new Database();
		
		
		d.deleteContact(contactid, "contact");
		
		
	}
	public ArrayList<Contact> searchContact(String fname) throws SQLException{
		Database d=new Database();
		//int cid=d.getContactId(fname);
		//System.out.println("Search="+cid);
		return d.SearchContact(fname, "contact");
		
	}
	
}
