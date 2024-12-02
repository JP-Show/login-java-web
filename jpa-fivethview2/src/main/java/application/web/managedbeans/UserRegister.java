package application.web.managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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
		try {
			Country country = new Country(this.country);
			user.setCountry(country);
			UsersServices.insertUser(user, user.getCreateAt().getZone());
			
		} catch (Exception e) {
			FacesContext faceContext = FacesContext.getCurrentInstance();
			faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			// Armazena a mensagem no Flash Scope para preservar após redirecionamento
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		// Redireciona para evitar reenvio do formulário		
		return "index?faces-redirect=true";	
	}
	
}
