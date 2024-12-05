package application.web.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import application.country.Country;
import application.users.User;
import application.users.UsersServices;
import application.web.exception.WebExceptions;

@ManagedBean
public class UserRegister {
	private User user = new User();
	private String country;

	private String password1;
	private String password2;

	public String registerUser() {
		try {
			if(!password1.equals(password2)) throw new WebExceptions("Password doesn't match");
			user.setPassword(password1);
			Country country = new Country(this.country);
			user.setCountry(country);
			UsersServices.insertUser(user, user.getCreateAt().getZone());
			return "login?faces-redirect=true";	
			
		} catch (Exception e) {
			FacesContext faceContext = FacesContext.getCurrentInstance();
			faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			// Armazena a mensagem no Flash Scope para preservar após redirecionamento
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		// Redireciona para evitar reenvio do formulário		
		return "signup?faces-redirect=true";
	}
	
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
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
