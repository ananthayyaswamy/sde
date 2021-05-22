import java.awt.geom.FlatteningPathIterator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class MVCPattern {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String args[]) throws Exception {
System.setProperty("java.security.auth.login.config", "jaas.config");

	


		int choice = -1;
		boolean condition = true;
		boolean f=true;
		int userid=-1;
	User user=new User();
	LoginContext loginContext=null;
	boolean login=false;
	
//		LoginContext loginContext=  newLogin();
//		Set<Principal> li=loginContext.getSubject().getPrincipals();
//		for(Principal l:li){
//		 userid=Integer.parseInt(l.getName());
//		}
//		user =userLogin( userid);
//		userid=user.getUserid();
		//System.out.println("user id in main="+userid);
		while (condition) {
		
			while(f){
System.out.println("1create user");
System.out.println("2 login");
				//System.out.println("3.LOGIN WITH EMAIL OTP");
				int choice1=sc.nextInt();
				switch (choice1){
				case 1:	
					sc.nextLine();
					System.out.println("Enter username");
					String username=sc.nextLine();
					System.out.println("Enter Password");
					String password=sc.nextLine();
					User user1=new User();
					user.setPassword(password);
					user.setUser(username);
					UserView userview1=new UserView();
					UserController userController1=new UserController(user1, userview1);
					int value =userController1.UserExits(username) ;

					if(value==0){
						System.out.println("CREATING NEW USER");
					userid=	userController1.addUser(username, password);
					//System.out.println("user id in login="+userid);
					user.setUserid(userid);
						System.out.println("new user created");
						codeGen code=new codeGen();
						String secretKey=code.generateRandom();
						
						userController1.addSecertKey(userid,secretKey);
						System.out.println("press enter to Login");
						sc.nextLine();
						
					}else{
						System.out.println("USER ALREADY EXITS");
						System.out.println("press enter to Login");
						sc.nextLine();
					}
					break;
				case 2:
				
				//sc.nextLine();
			if(userid==-1){
				String s="";
			 try {
				loginContext=  newLogin();
				
				Set<Principal> li=loginContext.getSubject().getPrincipals();
				for(Principal l:li){
				 userid=Integer.parseInt(l.getName());
				}
				user =userLogin( userid);
				System.out.println("user id in login="+userid);
				user.setUserid(userid);
				userid=user.getUserid();
			UserController userController=new UserController(user, new UserView());
		s=	userController.getsecretKey(userid);
		System.out.println("String got="+s);
		TimeBasedOneTimePasswordUtil t=new TimeBasedOneTimePasswordUtil();
		String genratedcode=t.generateCurrentNumberString(s);
		int i=0;
		while(i<5){
			sc.nextLine();
			System.out.println("enter TOTP");
			String code=sc.nextLine();
			System.out.println("code="+code);
			genratedcode=t.generateCurrentNumberString(s);
			System.out.println("gen="+genratedcode);
			if(code.equals(genratedcode)){
				System.out.println("LOGGED IN SUCCESFULLY");
				System.out.println("press enter to continue");
				sc.nextLine();
				login=true;
				f=false;
				break;
			}else{
				System.out.println("wrong password");
				System.out.println("press enter to continue");
				sc.nextLine();
			}
			i++;
		}
			} catch (LoginException e1) {
				// TODO Auto-generated catch block
				System.out.println();
			}
			
			}break;
				case 3:{
					if(userid==-1){
						//user=loginWithEmail();
						userid=user.getUserid();
						System.out.println("case3 :"+user.toString());
						//user.setUserid(userid);
						 if(userid!=-1){
							 f=false;
						 }
					}break;
				}
			}
			}
			UserContacts userContacts=new UserContacts();
			UserContactsView userContactsView=new UserContactsView();
			UserContactsController userContactsController=new UserContactsController(userContacts, userContactsView);
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			// clrscr();
			System.out.println("CONTACT MANAGEMENT SYSTEM");
			System.out.println("1.ADD CONTACTS");
			System.out.println("2.EDIT CONTACTS");
			System.out.println("3.DELETE CONTACTS");
			System.out.println("4.SEARCH CONTACTS");
			System.out.println("5.Exports CONTACTS");
			System.out.println("6.LOGOUT");
			System.out.println("0.EXIT");
			System.out.println("Please enter your choice");
			choice = sc.nextInt();
			String fname;
			String lname;

			switch (choice) {
			case 1:
				Contact contact = addContact();
				// Contact contact = new Contact();
				// contact.setFirstName("test");
				ContactView view = new ContactView();
				ContactController controller = new ContactController(contact, view);
				int cid1 = controller.addContact(contact);
				
				PhoneNumber p = new PhoneNumber();
				PhoneNumberView phoneNumberView = new PhoneNumberView();
				PhoneNumberController phoneNumberController = new PhoneNumberController(p, phoneNumberView);
				phoneNumberController.addPhone(cid1, contact);
				//System.out.println("cid in mvc=" + cid1);
				EmailAddress e = new EmailAddress();
				EmailAddressView emailAddressView = new EmailAddressView();
				EmailAddressController emailAddressController = new EmailAddressController(e, emailAddressView);
				emailAddressController.addEmailAddress(cid1, contact);
				Address a = new Address();
				AddressView addressView = new AddressView();
				AddressController addressController = new AddressController(a, addressView);
				addressController.addAddress(cid1, contact);
				userContacts.setUserId(userid);
				userContacts.setContactId(cid1);
				userContactsController.addUserContacts(userContacts);
				System.out.println("CONTACT ADDED SUCCESSFULLY");
				System.out.println("press enter to continue");
				sc.nextLine();
				break;

			case 2:
				newEditContact(userid);
				break;


			case 3:
				deleteContact(userid);

				break;

			case 4: {
				System.out.println("user id search="+userid);
				searchContacts(userid);
				break;

			}
			case 5:{
				export(user);
				
				System.out.println("press enter to continue");
				sc.nextLine();
				break;
			}
			case 6:{
				try {
					if(login){
						loginContext.logout();
						
					}
					userid=-1;
					f=true;
					
					System.out.println("You are logged out..");
					
					break;
				} catch (LoginException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			case 0:return;
			

		}}
	}
	
public static void export(User user1) throws SQLException, IOException{
	int userid=user1.getUserid();
	Contact contact=new Contact();
	ContactView contactView=new ContactView();
	ContactController contactController=new ContactController(contact, contactView);
	UserContacts user=new UserContacts();
	UserContactsView userContactsView= new UserContactsView();
	UserContactsController userContactsController =new UserContactsController(user, userContactsView);
	ArrayList<Contact> li=userContactsController.getContactid(userid);
	if(li.size()==0){
		System.out.println("NO MATCHING VALUES");
		System.out.println("press enter to continue");
		sc.nextLine();
		return;
	}
	PhoneNumberView phoneNumberView1 = new PhoneNumberView();
	PhoneNumber p1 = new PhoneNumber();
	PhoneNumberController phoneNumberController1 = new PhoneNumberController(p1, phoneNumberView1);
	phoneNumberController1.SearchContact(li);
	EmailAddress e1 = new EmailAddress();
	EmailAddressView emailAddressView1 = new EmailAddressView();
	EmailAddressController emailAddressController1 = new EmailAddressController(e1, emailAddressView1);
	emailAddressController1.SearchContact(li);
	Address a1 = new Address();
	AddressView addressView1 = new AddressView();
	AddressController addressController1 = new AddressController(a1, addressView1);
	addressController1.SearchContact(li);
	VCFFile vcfFile=new VCFFile();
	vcfFile.exportVcf(li, user1);
	System.out.println("CONTACT EXPORTED SUCCESSFULLY");
	
}
	private static Contact addContact() {
		int choice = -1;
		Contact contact = new Contact();
		sc.nextLine();
		System.out.println("Enter fisrtname");
		contact.setFirstName(sc.nextLine());

		System.out.println("Enter last name");
		contact.setLastName(sc.nextLine());

		System.out.println("Enter company name");
		contact.setCompany(sc.nextLine());

		System.out.println("Enter Phone  ");
		contact.setPhoneNumber(setPhoneValues(contact.getPhoneNumber()));
		System.out.println("Enter EMail");
		contact.setEmailAddress(setEmailValues(contact.getEmailAddress()));
		System.out.println("enter address");
		contact.setAddress(setAddressValues(contact.getAddress()));
		sc.nextLine();
		System.out.println("Enter dob");
		contact.setDob(sc.nextLine());
		return contact;
	}

	private static ArrayList setPhoneValues(ArrayList<PhoneNumber> map) {
		map = new ArrayList<>();
		int choice = -1;
		while (true) {
			System.out.println("to add Phonenumber press 1 ,to exit press 0");
			choice = sc.nextInt();

			String key = "", val = "";
			switch (choice) {

			case 1:
				sc.nextLine();
				System.out.println("enter type(home,work...)");

				key = sc.nextLine();
				System.out.println("enter value");
				val = sc.nextLine();
				PhoneNumber ph = new PhoneNumber(val, key);
				map.add(ph);

				break;

			case 0: {
				return map;
			}
			default:
				break;
			}

		}
		// return map;
	}

	private static ArrayList setEmailValues(ArrayList<EmailAddress> map) {
		map = new ArrayList<>();
		int choice = -1;
		while (true) {
			System.out.println("to add Email press 1 ,to exit press 0");
			choice = sc.nextInt();

			String key = "", val = "";
			switch (choice) {

			case 1:
				sc.nextLine();
				System.out.println("enter type(home,work...)");

				key = sc.nextLine();
				System.out.println("enter value");
				val = sc.nextLine();
				EmailAddress ph = new EmailAddress(val, key);
				map.add(ph);

				break;

			case 0: {
				return map;
			}
			default:
				break;
			}

		}
		// return map;
	}

	private static ArrayList setAddressValues(ArrayList<Address> map) {
		map = new ArrayList<>();
		int choice = -1;
		while (true) {
			System.out.println("to add Address press 1 ,to exit press 0");
			choice = sc.nextInt();

			String key = "", val = "";
			switch (choice) {

			case 1:

				sc.nextLine();
				System.out.println("enter type(home,work...)");

				key = sc.nextLine();
				System.out.println("enter value");
				val = sc.nextLine();
				Address ph = new Address(val, key);
				map.add(ph);

				break;

			case 0: {
				return map;
			}
			default:
				break;
			}

		}
		// return map;
	}

	public static int editContact() {
		int choice;

		System.out.println("1. Edit firstname");
		System.out.println("2. Edit lastname");
		System.out.println("3. Edit company");
		System.out.println("4. Edit dob");
		System.out.println("5. Edit Phone number");

		System.out.println("6. Edit  email");

		System.out.println("7. Edit  Address");

		// System.out.println("0. Exit");
		choice = sc.nextInt();

		return choice;
	}

	public static User userLogin(int userid){
		//int userid=-1;
//		while(true){
//		 System.out.println("1.Login In");
//		
//		 System.out.println("Enter 1 to continue");
//		 int choice=sc.nextInt();
//		switch(choice){
//		case 1:{
//			//System.out.println("LOGIN");
//			sc.nextLine();
//			System.out.println("Enter username");
//			String username=sc.nextLine();
//			System.out.println("Enter Password");
//			String password=sc.nextLine();
//			User user=new User();
//			user.setPassword(password);
//			user.setUser(username);
//			UserView userview=new UserView();
//			UserController userController=new UserController(user, userview);
//			int value =userController.UserExits(username) ;
//			
//			if(value==0){
//				System.out.println("CREATING NEW USER");
//			userid=	userController.addUser(username, password);
//			//System.out.println("user id in login="+userid);
//			user.setUserid(userid);
//				System.out.println("new user created");
//				System.out.println("press enter to Login");
//				sc.nextLine();
//			}else{
//				//System.out.println("else");
//				userid=userController.getUserId(username,password);
//				user.setUserid(userid);
//				if(userid==-1){
//					System.out.println("Wrong Credentials try agin");
//					System.out.println("press enter to continue");
//					sc.nextLine();
//					break;
//				}else{
//					System.out.println("LOGGED IN SUCCESSFULLY");
//					
//						
//						System.out.println("press enter to continue");
//						sc.nextLine();
//					
//					return user;
//				}
//				
//			}
//			break;
//		}
//		}
//		}
		User user=new User();
		UserView userview=new UserView();
		UserController userController=new UserController(user, userview);
	user=	userController.getUserDetails(user);
	return user;
	}
	
	
	public static void newEditContact(int userid) throws SQLException{
		sc.nextLine();
		System.out.println("Enter first name");
		String fname=sc.nextLine();
		System.out.println("Enter last name");
		String lname=sc.nextLine();
	
		Contact contact=new Contact();
		ContactView contactView=new ContactView();
		ContactController contactController=new ContactController(contact, contactView);
		UserContacts user=new UserContacts();
		UserContactsView userContactsView= new UserContactsView();
		UserContactsController userContactsController =new UserContactsController(user, userContactsView);
		ArrayList<Contact> li=userContactsController.getContactid(userid, fname, lname);
		//System.out.println(li);
		if(li.size()==0){
			System.out.println("NO MATCHING VALUES");
			System.out.println("press enter to continue");
			sc.nextLine();
			return;
		}
		for(Contact c:li){
			contactController.display(c);
		}
		System.out.println("confirm the contact id of record to be deleted");
		int contactid=sc.nextInt();
		int choice =-1;

		System.out.println("1. Edit firstname");
		System.out.println("2. Edit lastname");
		System.out.println("3. Edit company");
		System.out.println("4. Edit dob");
		System.out.println("5. Edit Phone number");
		System.out.println("6. Edit  email");
		System.out.println("7. Edit  Address");
		System.out.println("Enter choice to continue");
		choice=sc.nextInt();
		switch (choice){
		case 1:
			contactController.editContact(contactid,"firstname");
			break;
		case 2:
			contactController.editContact(contactid,"lastname");
			break;
		case 3:
			contactController.editContact(contactid,"company");
			break;
		case 4:
			contactController.editContact(contactid,"dob");
			break;
		case 5:
			PhoneNumberView phoneNumberView1 = new PhoneNumberView();
			PhoneNumber p1 = new PhoneNumber();
			PhoneNumberController phoneNumberController1 = new PhoneNumberController(p1, phoneNumberView1);
			phoneNumberController1.editContact(contactid);
			break;
		case 6:
			EmailAddress e1 = new EmailAddress();
			EmailAddressView emailAddressView1 = new EmailAddressView();
			EmailAddressController emailAddressController1 = new EmailAddressController(e1, emailAddressView1);
			emailAddressController1.editContact(contactid);
			break;
		case 7:
			Address a1 = new Address();
			AddressView addressView1 = new AddressView();
			AddressController addressController1 = new AddressController(a1, addressView1);
			addressController1.editContact(contactid);
			break;
			
		}
		
		
	}
	public static void deleteContact(int userid) throws SQLException{
		sc.nextLine();
		System.out.println("Enter first name");
		String fname=sc.nextLine();
		System.out.println("Enter last name");
		String lname=sc.nextLine();
		Contact contact=new Contact();
		ContactView contactView=new ContactView();
		ContactController contactController=new ContactController(contact, contactView);
		UserContacts user=new UserContacts();
		UserContactsView userContactsView= new UserContactsView();
		UserContactsController userContactsController =new UserContactsController(user, userContactsView);
		ArrayList<Contact> li=userContactsController.getContactid(userid, fname, lname);
		//System.out.println(li);
		if(li.size()==0){
			System.out.println("NO MATCHING VALUES");
			System.out.println("press enter to continue");
			sc.nextLine();
			return;
		}
		for(Contact c:li){
			contactController.display(c);
		}
		System.out.println("confirm the contact id of record to be deleted");
		int contactid=sc.nextInt();
		PhoneNumberView phoneNumberView1 = new PhoneNumberView();
		PhoneNumber p1 = new PhoneNumber();
		PhoneNumberController phoneNumberController1 = new PhoneNumberController(p1, phoneNumberView1);
		phoneNumberController1.deleteContact(contactid);
		EmailAddress e1 = new EmailAddress();
		EmailAddressView emailAddressView1 = new EmailAddressView();
		EmailAddressController emailAddressController1 = new EmailAddressController(e1, emailAddressView1);
		emailAddressController1.deleteContact(contactid);
		Address a1 = new Address();
		AddressView addressView1 = new AddressView();
		AddressController addressController1 = new AddressController(a1, addressView1);
		addressController1.deleteContact(contactid);
		userContactsController.deleteUser(contactid);
		contactController.deleteContact(contactid);
	}
	
	public static void searchContacts(int userid) throws SQLException{
		sc.nextLine();
		System.out.println("Enter first name");
		String fname=sc.nextLine();
		System.out.println("Enter last name");
		String lname=sc.nextLine();
		Contact contact=new Contact();
		ContactView contactView=new ContactView();
		ContactController contactController=new ContactController(contact, contactView);
		UserContacts user=new UserContacts();
		UserContactsView userContactsView= new UserContactsView();
		UserContactsController userContactsController =new UserContactsController(user, userContactsView);
		ArrayList<Contact> li=userContactsController.getContactid(userid, fname, lname);
		if(li.size()==0){
			System.out.println("NO MATCHING VALUES");
			System.out.println("press enter to continue");
			sc.nextLine();
			return;
		}
		PhoneNumberView phoneNumberView1 = new PhoneNumberView();
		PhoneNumber p1 = new PhoneNumber();
		PhoneNumberController phoneNumberController1 = new PhoneNumberController(p1, phoneNumberView1);
		phoneNumberController1.SearchContact(li);
		EmailAddress e1 = new EmailAddress();
		EmailAddressView emailAddressView1 = new EmailAddressView();
		EmailAddressController emailAddressController1 = new EmailAddressController(e1, emailAddressView1);
		emailAddressController1.SearchContact(li);
		Address a1 = new Address();
		AddressView addressView1 = new AddressView();
		AddressController addressController1 = new AddressController(a1, addressView1);
		addressController1.SearchContact(li);
		//System.out.println(li);
		for(Contact c:li){
			contactController.display(c);
			phoneNumberController1.display(c.getPhoneNumber());
			emailAddressController1.display(c.getEmailAddress());
			addressController1.display(c.getAddress());
		}
		System.out.println("press enter to continue");
		sc.nextLine();
	}

public static LoginContext newLogin() throws LoginException{
int userid;
LoginContext loginContext=null;
boolean flag=false;
while(!flag){
	 loginContext=new LoginContext("UserConfig",new UserCallbackHandler());
	loginContext.login();
	 flag=true;
	System.out.println("hiii"+loginContext.getSubject().getPrincipals());
	
	
}
return loginContext;
}

//public static User loginWithEmail() throws Exception{
//	int userid=-1;
//	sc.nextLine();
//	boolean flag=false;
//	System.out.println("ENTER EMAIL");
//	String Email=sc.nextLine();
//	OTP Otp=new OTP();
//	int otp=Otp.generateOTP();
//	SendMail s=new SendMail();
//	//s.sendMail(Email, otp+"");
//	User user1=new User();
//	UserView userview1=new UserView();
//	UserController userController1=new UserController(user1, userview1);
//	int exists=userController1.UserExits(Email);
//	Email email=new Email();
//	//EmailControler emailControler=new EmailControler(email);
//	if(exists==0){
//		user1.setPassword(otp+"");
//		user1.setUser(Email);
//		userController1.addUser(Email, otp+"");
//		
//		
//		
//	}else{
//		user1.setUser(Email);
//		user1.setPassword(otp+"");
//		userController1.updateUser(user1);
//		
//		
//	}
//	email.setEmail(Email);
//	//System.out.println(email.getEmail());
//	email.setOtp(otp+"");
//	//System.out.println(email.getOtp());
//	email.setInitialTime(Otp.getTime());
//	//System.out.println(email.getInitialTime());
//	
//	EmailControler emailControler=new EmailControler(email);
//	emailControler.addEmailInitialTime(email.getInitialTime(),email.getEmail(),email.getOtp() );
//	
//	
//	System.out.println("OTP SENT");
//	System.out.println("Please Enter the otp ");
//	int userInputOtp=sc.nextInt();
//	String finaltime=Otp.getTime();
//	boolean result=Otp.validateOtp(userInputOtp+"",email.getOtp(), Otp.differenceTime(email.getInitialTime(), finaltime));
//	if(result==true){
//	userid=	user1.getUserId(user1.getUser(), user1.getPassword());
//	user1.setUserid(userid);
//	//System.out.println("user id in email login:"+userid);
//	}
//	
//	return user1;
//	
//}
//


}