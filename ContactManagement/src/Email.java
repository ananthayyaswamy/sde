
public class Email {
private String email;
private String otp;
private String initialTime;
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getOtp() {
	return otp;
}
public void setOtp(String otp) {
	this.otp = otp;
}
public String getInitialTime() {
	return initialTime;
}
public void setInitialTime(String initialTime) {
	this.initialTime = initialTime;
}
//public void addEmailInitialTime(String time,String email,String otp){
//	Database d=new Database();
//	d.addEmailInitialTime(time, email,otp);
//}
}
