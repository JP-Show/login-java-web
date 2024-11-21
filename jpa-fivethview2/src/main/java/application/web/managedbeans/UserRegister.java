package application.web.managedbeans;

import javax.faces.bean.ManagedBean;

import application.users.User;

@ManagedBean
public class UserRegister {
	//isso precisa ser dadasldkj
	private User user = new User();
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void registerUser() {
		System.out.println(user.toString());
	}
	
}
