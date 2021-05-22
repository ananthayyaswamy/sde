import java.io.Serializable;
import java.security.Principal;

public class User implements Principal,Serializable{
	private String User;
	private String password;
	private int userid;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int userExists(String username){
		Database d=new Database();
		return d.UserExists(username);
	}
public int addUser(String username,String password){
	Database d=new Database();
	return d.addUser(username, password);
}
public int getUserId(String username,String password){
	Database d=new Database();
	return d.getUserId(username,password);
}
@Override
public String toString() {
	return "User [User=" + User + ", password=" + password + ", userid=" + userid + "]";
}
public User getUserDetails(User user){
	Database d=new Database();
	return d.getUserDetails(user);
}

public User(String user, String password, int userid) {
	super();
	User = user;
	this.password = password;
	this.userid = userid;
}
public User() {
	// TODO Auto-generated constructor stub
}
@Override
public String getName() {
	// TODO Auto-generated method stub
	return getUserid()+"";
}
public void updateUser(User user){
	Database d=new Database();
	d.updateUser(user);
}
public String getPassword() {
	return password;
}

public void addSecertKey(int userid,String secretKey){
	Database d=new Database();
	d.addSecretKey(userid,secretKey );
}

public String getSecretKey(int userid){
	Database d=new Database();
	return d.getSecretKey(userid);
}
}
