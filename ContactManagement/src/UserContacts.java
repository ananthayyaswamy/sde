import java.sql.SQLException;
import java.util.ArrayList;

public class UserContacts {
private int userId;
private int contactId;
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getContactId() {
	return contactId;
}
public void setContactId(int contactId) {
	this.contactId = contactId;
}

public void addUserContacts(UserContacts u ){
	Database d=new Database();
	d.addUserContacts(u);
}

//public ArrayList<Integer> getContactId(int userid){
//	Database d=new Database();
//	return d.getContactid(userid);
//}
public ArrayList getContactId(int userid,String fname,String lname){
	Database d=new Database();
	return d.getContactid(userid, fname, lname);
}
public ArrayList getContactId(int userid){
	Database d=new Database();
	return d.getContactid(userid);
}
public void DeleteUserContacts(int contactid) throws SQLException{
	
	Database d=new Database();
	d.deleteContact(contactid, " usercontacts");
	
}
}
