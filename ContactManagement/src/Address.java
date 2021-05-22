import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Address {
	private String address;
	public Address(String address, String type) {
		super();
		this.address = address;
		this.type = type;
	}
	public Address() {
		// TODO Auto-generated constructor stub
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String type;
	public void addAddress(int contactid,Contact contact) throws SQLException{
		Database db=new Database();
		db.addAddressIntoDatabase(contact, contactid);
	}
	public void editContact(int contactid ) throws SQLException{
		Scanner sc=new Scanner(System.in);
		Database d=new Database();
		
		System.out.println("enter the type of Address you want to edit");
		String type=sc.nextLine();
		System.out.println("Enter the new value");
		String value=sc.nextLine();
		d.editTypeContact(contactid, "address", value, "address", type);
		
	}

	public void deleteContact(int contactid) throws SQLException{
		Database d=new Database();
		
		
		d.deleteContact(contactid, "address");
		
		
	}
	public ArrayList<Contact> SearchContact(ArrayList<Contact> li) throws SQLException{
		Database d=new Database();
		for( Contact contact:li){
			int contactid=d.getContactId(contact.getFirstName());
			contact=d.searchAddress(contact, contactid, "address");
		}
		return li;
	}
}
