package application.web.managedbeans;

import javax.faces.bean.ManagedBean;

import application.country.Country;
import application.users.User;
import application.users.UsersServices;

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
	
	public String registerUser() {
		Country country = new Country(this.country);
		user.setCountry(country);
		UsersServices.insertUser(user, user.getCreateAt().getZone());
		System.out.println(user.getCreateAt().getZone());
		// Redireciona para evitar reenvio do formulário
		return "index?faces-redirect=true";
	}
	
}
