package application.web.managedbeans;

import javax.faces.bean.ManagedBean;

import application.country.Country;
import application.users.User;

@ManagedBean
public class UserRegister {
	private User user = new User();
	private String country;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void registerUser() {
		Country country = new Country(this.country);
		user.setCountry(country);
		System.out.println(user.toString());
	}
	
}
