import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class VCFFile {
public void exportVcf(ArrayList<Contact>li,User user) throws IOException{
	File f=new File(user.getUser()+".vcf");
	  FileOutputStream fop=new FileOutputStream(f);
	for(Contact c:li){
		String phone="";
		for(PhoneNumber ph:c.getPhoneNumber()){
			 phone=phone+"TEL;TYPE="+ph.getType()+";"+ph.getPhoneNumber()+"\n";
		}
		String email="";
		for(EmailAddress ph:c.getEmailAddress()){
			 email=email+"EMAIL;TYPE="+ph.getType()+";"+ph.getEmailAddress()+"\n";
		}
		String address="";
		for(Address ph:c.getAddress()){
			 address=address+"ADDRESS;TYPE="+ph.getType()+";"+ph.getAddress()+"\n";
		}
		String str="BEGIN:VCARD\n"+"VERSION:4.0\n"+"FN:"+c.getFirstName()+" "+c.getLastName()+" \n"+
	"Company: "+c.getCompany()+"\n"+""+phone+""+email+""+address+"BDAY: "+c.getDob()+"\n"+"END:VCARD\n"  ;
		System.out.println();
		fop.write(str.getBytes());
		
	}

	
	
}
}
