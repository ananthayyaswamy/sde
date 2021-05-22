
public class ContactView {
	public void show(Contact contact) {
		System.out.println("firstname : " + contact.getFirstName());
		System.out.println("lastname : " + contact.getLastName());
		System.out.println("company :" + contact.getCompany());
		System.out.println("dob : " + contact.getDob());
		System.out.println("cid :"+ contact.getContactid());
	}
}
