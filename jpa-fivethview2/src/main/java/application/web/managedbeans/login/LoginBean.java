package application.web.managedbeans.login;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import application.users.User;
import application.users.UsersServices;

@SessionScoped
@ManagedBean
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	private User userLogged = null;

	public String logar() {
		if(userLogged == null) {
			User userFound = UsersServices.findByNameUnique(name);
			try {
				Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, 64, 2, 128000, 10);
				if(userFound.getName().equals(name) && argonEncoder.matches
						(password, userFound.getPassword())) {
					userLogged = userFound;
					System.out.println("logado com sucesso");
					return "logged/home.xhtml?faces-redirect=true";
				}
				System.out.println("teste");
				FacesContext faceContext = FacesContext.getCurrentInstance();
				faceContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name or password is wrong", null));
	            
				// Armazena a mensagem no Flash Scope para preservar após redirecionamento
	            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	           
	            // Redireciona de volta para login.xhtml
	            return "/logged/home.xhtml?faces-redirect=true";
	            
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}			
		}
		return "/logged/home.xhtml?faces-redirect=true";
	}


	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		userLogged = null;
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(User userLogged) {
		this.userLogged = userLogged;
	}

}
