package application.web.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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
	
	public String registerUser() {
		Country country = new Country(this.country);
		user.setCountry(country);
		System.out.println(user.toString());
		// Redireciona para evitar reenvio do formulário
		return "index?faces-redirect=true";
	}
	
}
