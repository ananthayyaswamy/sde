import java.sql.SQLException;
import java.util.ArrayList;

public class UserContactsController {
private UserContacts model;
private UserContactsView view;
public UserContactsController(UserContacts model, UserContactsView view) {
	super();
	this.model = model;
	this.view = view;
}

public void addUserContacts(UserContacts u){
	model.addUserContacts(u);
}
//public ArrayList getContactid(int userid){
//	return model.getContactId(userid);
//	
//}
public ArrayList getContactid(int userid,String fname,String lname){
	return model.getContactId(userid, fname, lname);
	
}
public ArrayList getContactid(int userid){
	return model.getContactId(userid);
	
}
public void deleteUser(int contactid) throws SQLException{
	model.DeleteUserContacts(contactid);
}
}
