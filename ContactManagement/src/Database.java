import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {
	private final String url="jdbc:postgresql://localhost/contactdb";
	private final String user="postgres";
	private final String password="mysql";
	static Scanner sc=new Scanner(System.in); 
public  Connection connect(){
	Connection connection=null;
	try{
		 connection=DriverManager.getConnection(url,user,password);
		 if(connection!=null){
			// System.out.println("connected");
		 }else{
			 System.out.println("failed to connect");
		 }
	}catch(SQLException e){
		System.out.println(e.toString());
	}
	return connection;
}

public int addContactIntoDatabase(Contact contact) throws SQLException{
	String query="Insert into contact(firstname,lastname,company,dob) values('"+contact.getFirstName()+"','"+contact.getLastName()+"','"+contact.getCompany()+"','"+contact.getDob()+"') returning contactid;";
Connection connection=connect();
//Statement statement=connection.createStatement();
PreparedStatement statement=connection.prepareStatement(query);
statement.execute();
ResultSet rs=statement.getResultSet();
rs.next();
int contactid=rs.getInt(1);
//System.out.println("inserted id="+contactid);
connection.close();
//addPhoneNumberIntoDatabase(contact, contactid);
return contactid;
}
public void addUserContacts(UserContacts u){
	Connection connection=connect();
	String query="Insert into usercontacts(userid,contactid) values('"+u.getUserId()+"','"+u.getContactId()+"') ";
	Statement statement;
	try {
		statement = connection.createStatement();
		statement.executeUpdate(query);
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
public int addUser(String username,String password ){
	Connection connection=connect();
	String query="Insert into usertable(username,password) values('"+username+"','"+password+"') returning userid;";
    PreparedStatement statement;
	int userid=-1;
    try {
		statement = connection.prepareStatement(query);
		statement.execute();
		ResultSet rs=statement.getResultSet();
		rs.next();
		 userid=rs.getInt(1);
		 connection.close();
		//System.out.println("user id="+userid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return userid;
    
}
public void addSecretKey(int userid,String secretKey){
	Connection connection=connect();
	
	String query="Insert into secertkey(userid,secret) values("+userid+",'"+secretKey+"');";
	Statement statement;
	try {
		statement=connection.createStatement();
		statement.executeUpdate(query);
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}public void addEmailIntoDatabase(Contact contact,int contactid) throws SQLException{
	Connection connection=connect();
	for(EmailAddress ph:contact.getEmailAddress()){
		String query="Insert into email(emailaddress,type,contactid) values('"+ph.getEmailAddress()+"','"+ph.getType()+"','"+contactid+"') ;";
		Statement statement=connection.createStatement();
		statement.executeUpdate(query);
		
	}
	connection.close();
}
public User getUserDetails(User user){
	Connection connection=connect();
	String query="Select * from usertable where userid ="+user.getUserid()+";";
	PreparedStatement statement;
	try {
		statement=connection.prepareStatement(query);
		statement.execute();
		ResultSet rs=statement.getResultSet();
		rs.next();
		while(rs.next()){
		user.setUser(rs.getString(2));
		user.setPassword(rs.getString(3));
		connection.close();}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return user;
}
public String getSecretKey(int userid){
Connection connection=connect();
String query="Select * from secertkey where userid="+userid+";";
PreparedStatement statement;
String s="";
try {
	statement=connection.prepareStatement(query);
	statement.execute();
	ResultSet rs=statement.getResultSet();
	//rs.next();
	
	while(rs.next()){
		s= rs.getString(2);
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return s;
}
public int getContactId(String fname,String lname) throws SQLException{
	//ArrayList<Integer> cid=new ArrayList<>();
	int contactid=0;
	String query="Select contactid from contact where firstname='"+fname+"'and lastname='"+lname+"';";
	Connection connection=connect();
	PreparedStatement statement;
	try {
		statement = connection.prepareStatement(query);
		statement.execute();
		ResultSet rs=statement.getResultSet();

		rs.next();
		 contactid=rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("wrong data");
	}
	connection.close();
	return contactid;
	
}
public int getContactId(String fname) throws SQLException{
	int contactid=0;
	String query="Select contactid from contact where firstname='"+fname+"';";
	Connection connection=connect();
	PreparedStatement statement;
	try {
		statement = connection.prepareStatement(query);
		statement.execute();
		ResultSet rs=statement.getResultSet();
		if(rs.next()==false){
			System.out.println("NO RECORD FOUND");
			return -1;
		}
		//rs.next();
		 contactid=rs.getInt(1);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	connection.close();
	return contactid;
	
}
public void editContact(int contactid,String field,String val,String table) throws SQLException{
	Connection connection=connect();
	String query="update "+table+" set "+field+"='"+val+"' where contactid="+contactid+";";
	Statement statement;
	try {
		statement = connection.createStatement();
		int rows=statement.executeUpdate(query);
		if(rows==0){
			System.out.println("SORRY NO ROWS UPDATED");
			System.out.println("press enter to continue");
			sc.nextLine();
			return;
		}else{
			System.out.println("UPDATED SUCCESSFULLY");
			System.out.println("press enter to continue");
			sc.nextLine();
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
	connection.close();
}
public int UserExists(String username){
	Connection connection=connect();
	String query="select userid from usertable where username='"+username+"';";
	Statement statement;
	int rows=0;
	try {
		statement = connection.createStatement();
		 statement.execute(query);
		ResultSet rs=statement.getResultSet();
		boolean recordFound=rs.next();
		rs.next();
		 if(recordFound){
			 rows++;
		 }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return rows;
	
}
public void updateUser(User user){
	Connection connection=connect();
	String query="update usertable set password='"+user.getPassword()+"' where username='"+user.getUser()+"';";
	Statement statement;
	try {
		statement=connection.createStatement();
		statement.executeUpdate(query);
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void editTypeContact(int contactid,String field,String val,String table,String type) throws SQLException{
	Connection connection=connect();
	String query="update "+table+" set "+field+"='"+val+"' where contactid="+contactid+" and type='"+type+"';";
	Statement statement;
	try {
		statement = connection.createStatement();
		int rows=statement.executeUpdate(query);
		if(rows==0){
			System.out.println("SORRY NO ROWS UPDATED");
			System.out.println("press enter to continue");
			sc.nextLine();
			return;
		}else{
			System.out.println("UPDATED SUCCESSFULLY");
			System.out.println("press enter to continue");
			sc.nextLine();
		}
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	connection.close();
	
}

public int deleteContact(int contactid,String table) throws SQLException{
	Connection connection=connect();
	String query="delete from "+table+" where contactid = "+contactid+" ;";
	Statement statement;
	try{
		statement=connection.createStatement();
		int rows=statement.executeUpdate(query);
		if(rows==0){
			System.out.println("SORRY NO ROWS UPDATED");
			System.out.println("press enter to continue");
			connection.close();
			sc.nextLine();
			return -1;
		}else{
//			System.out.println("UPDATED SUCCESSFULLY");
//			System.out.println("press enter to continue");
//			sc.nextLine();
			return 1;
		}
		
	}catch (SQLException e) {
		
		e.printStackTrace();
	}
	connection.close();
	return 1;
}

public void addPhoneNumberIntoDatabase(Contact contact,int contactid) throws SQLException{
	Connection connection=connect();
	for(PhoneNumber ph:contact.getPhoneNumber()){
		String query="Insert into mobilenumber(mobilenumber,type,contactid) values('"+ph.getPhoneNumber()+"','"+ph.getType()+"','"+contactid+"') ;";
		Statement statement=connection.createStatement();
		statement.executeUpdate(query);
		
	}
	connection.close();
}





public void addAddressIntoDatabase(Contact contact,int contactid) throws SQLException{
	Connection connection=connect();
	for(Address ph:contact.getAddress()){
		String query="Insert into address(address,type,contactid) values('"+ph.getAddress()+"','"+ph.getType()+"','"+contactid+"') ;";
		Statement statement=connection.createStatement();
		statement.executeUpdate(query);
		
	}
	connection.close();
}
public int getUserId(String fname,String password){
	Connection connection=connect();
	String query="select * from usertable where username = '"+fname+"' and password ='"+password+"' ;";
	Statement statement;
	int userid=-1;
	try {
		statement =connection.createStatement();
		ResultSet rs=statement.executeQuery(query);
		connection.close();
		while(rs.next()){
			//System.out.println(rs.getInt(1)); 
		userid=rs.getInt(1);	
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return userid;
}
public ArrayList<Contact> SearchContact(String fname,String table) throws SQLException{
	Connection connection=connect();
	int count=0;
	String query="select * from "+table+" where firstname='"+fname+"';";
	ArrayList<Contact> li=new ArrayList<>();
	Statement statement;
	try {
		statement = connection.createStatement();
		ResultSet rs=statement.executeQuery(query);
		//System.out.println("Executed");

		while(rs.next()){
			count++;
			Contact c=new Contact();
			c.setFirstName(rs.getString(2));
			c.setLastName(rs.getString(3));
			c.setCompany(rs.getString(4));
			c.setDob(rs.getString(5));
			li.add(c);
			//System.out.println("contactSearch"+li);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(count==0){
	  System.out.println("SORRY NO RECORDS FOUND");
	  System.out.println("PRESS ENTER TO CONTINUE");
	  sc.nextLine();
	  return li;
	}
	connection.close();
	return li;
	
}
public Contact searchMobile(Contact contact,int contactid,String table ){
	Connection connection=connect();
	String query="select * from "+table+" where contactid="+contactid+";";
	ArrayList<PhoneNumber> pli=new ArrayList<>();
	try {
	Statement statement=connection.createStatement();
	ResultSet rs=statement.executeQuery(query);
	

		//System.out.println("search mobile executed");
		while(rs.next()){
			
			PhoneNumber ph=new PhoneNumber();
			ph.setPhoneNumber(rs.getString(3));
			ph.setType(rs.getString(4));
			pli.add(ph);
			//System.out.println("list"+pli);
		}
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	contact.setPhoneNumber(pli);
	//System.out.println("get in search ephone"+contact.getPhoneNumber());
	try {
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return contact;
}
public Contact searchAddress(Contact contact,int contactid,String table ){
	Connection connection=connect();
	String query="select * from "+table+" where contactid="+contactid+";";
	ArrayList<Address> pli=new ArrayList<>();
	try {
	Statement statement=connection.createStatement();
	ResultSet rs=statement.executeQuery(query);
	

	//System.out.println("search address executed");
		while(rs.next()){
			
			Address ph=new Address();
			ph.setAddress(rs.getString(3));
			ph.setType(rs.getString(4));
			pli.add(ph);
			//System.out.println("list"+pli);
		}
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	contact.setAddress(pli);
	//System.out.println("get in search address"+contact.getAddress());
	return contact;
}
public Contact searchEmail(Contact contact,int contactid,String table ){
	Connection connection=connect();
	String query="select * from "+table+" where contactid="+contactid+";";
	ArrayList<EmailAddress> pli=new ArrayList<>();
	try {
	Statement statement=connection.createStatement();
	ResultSet rs=statement.executeQuery(query);

	//System.out.println("search email executed");
		while(rs.next()){
			EmailAddress ph=new EmailAddress();
			
			ph.setEmailAddress(rs.getString(3));
			ph.setType(rs.getString(4));
			pli.add(ph);
			//System.out.println("list"+pli);
		}
		connection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	contact.setEmailAddress(pli);
	//System.out.println("get in search email"+contact.getEmailAddress());
	return contact;
}

//public ArrayList<Integer> getContactid(int userid){
//	Connection connection=connect();
//	ArrayList<Integer> li=new ArrayList<>();
//	String query="select contactid from usercontacts where userid="+userid+";";
//	Statement statement;
//	try {
//		statement = connection.createStatement();
//		ResultSet rs=statement.executeQuery(query);
//		while(rs.next()){
//			li.add(rs.getInt(1));
//		}
//		connection.close();
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	return li;
//}
public ArrayList getContactid(int userid,String fname,String lname){
	int contactid=-1;
	ArrayList<Contact> li=new ArrayList<>();
	Connection connection=connect();
	String query="select * from contact where firstname='"+fname+"' and lastname='"+lname+"' and contactid in(select contactid from usercontacts where userid="+userid+"); ";
	Statement statement;
	try {
		statement=connection.createStatement();
		ResultSet rs=statement.executeQuery(query);
		while(rs.next()){
			Contact contact =new Contact();
			contact.setContactid(rs.getInt(1));
			contact.setFirstName(rs.getString(2));
			contact.setLastName(rs.getString(3));
			contact.setCompany(rs.getString(4));
			contact.setDob(rs.getString(5));
			
			li.add(contact);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return li;
}
public ArrayList getContactid(int userid){
	int contactid=-1;
	ArrayList<Contact> li=new ArrayList<>();
	Connection connection=connect();
	//System.out.println("userid="+userid);
	String query="select * from contact where contactid in(select contactid from usercontacts where userid="+userid+"); ";
	Statement statement;
	try {
		statement=connection.createStatement();
		ResultSet rs=statement.executeQuery(query);
		while(rs.next()){
			Contact contact =new Contact();
			contact.setContactid(rs.getInt(1));
			contact.setFirstName(rs.getString(2));
			contact.setLastName(rs.getString(3));
			contact.setCompany(rs.getString(4));
			contact.setDob(rs.getString(5));
			
			li.add(contact);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return li;
}
}

