import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneNumber {
private String phoneNumber;
public PhoneNumber(String phoneNumber, String type) {
	super();
	this.phoneNumber = phoneNumber;
	this.type = type;
}
public PhoneNumber() {
	// TODO Auto-generated constructor stub
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
private String type;
public void addPhone(int contactid,Contact contact) throws SQLException{
	Database db=new Database();
	db.addPhoneNumberIntoDatabase(contact, contactid);
}
public void editContact(int contactid ) throws SQLException{
	Scanner sc=new Scanner(System.in);
	Database d=new Database();
	
	System.out.println("enter the type of mobile number you want to edit");
	String type=sc.nextLine();
	System.out.println("Enter the new value");
	String value=sc.nextLine();
	d.editTypeContact(contactid, "mobilenumber", value, "mobilenumber", type);
	
}

public int deleteContact(int contactid) throws SQLException{
Database d=new Database();


return d.deleteContact(contactid, "mobilenumber");


}
public ArrayList<Contact> SearchContact(ArrayList<Contact> li) throws SQLException{

Database d=new Database();
for( Contact contact:li){
	//System.out.println("in phone controller"+contact.getDob());
	int contactid=d.getContactId(contact.getFirstName());
	//System.out.println("contact id="+contactid);
contact=	d.searchMobile(contact, contactid, "mobilenumber");
	
}
return li;
}
}
