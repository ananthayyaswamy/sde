import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class UserLoginModule implements LoginModule{
	
private Subject subject=null;
private CallbackHandler callbackHandler=null;
private User userPrincipal=null;
	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		System.out.println("User LOGIN MODULE ABORT");
		if(subject!=null&&userPrincipal!= null&&subject.getPrincipals().contains(userPrincipal)){
			subject.getPrincipals().remove(userPrincipal);
		}
		subject=null;
		userPrincipal=null;
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		boolean flag=false;
		System.out.println("User LOGIN MODULE COMMIT");
		if(subject!=null&&!subject.getPrincipals().contains(userPrincipal)){
			subject.getPrincipals().add(userPrincipal);
		 
			System.out.println();
			//subject.getPublicCredentials().add(userPrincipal);
			flag=true;
		}
		return flag;
	}

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		// TODO Auto-generated method stub
		this.callbackHandler=callbackHandler;
		this.subject=subject;
		System.out.println("User LOGIN MODULE INITIALIZE");
		
	}

	@Override
	public boolean login() throws LoginException {
		// TODO Auto-generated method stub
		boolean flag=false;
		System.out.println("User LOGIN MODULE LOGIN");
		Callback[] callbackArray=new Callback[2];
		callbackArray[0]=new NameCallback("User Name:");
		callbackArray[1]=new PasswordCallback("Password", false);
		try {
			callbackHandler.handle(callbackArray);
			String name=((NameCallback)callbackArray[0]).getName();
			String password=new String(((PasswordCallback)callbackArray[1]).getPassword());
			User user=new User();
			int userid=user.getUserId(name, password);
			System.out.println("user id in login module="+userid);
			if(userid!=-1){
				flag=true;
				userPrincipal=new User(name,password,userid);
				
				System.out.println("new name="+userPrincipal.getName());
				System.out.println("authentication success......");
				return flag;
			}
			if(flag==false) System.out.println("Authentication failure...");
		} catch (IOException |UnsupportedCallbackException e) {
			// TODO Auto-generated catch block
			System.out.println();
		} 
		return flag;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		System.out.println("User LOGIN MODULE LOGOUT");
		subject.getPrincipals().remove(userPrincipal);
		subject=null;
		return true;
	}

}
