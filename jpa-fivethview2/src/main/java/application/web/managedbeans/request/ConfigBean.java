package application.web.managedbeans.request;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import application.users.User;
import application.users.UsersServices;
import application.utils.ArgonInstance;
import application.web.exception.WebExceptions;
import application.web.managedbeans.session.LoginBean;

@RequestScoped
@ManagedBean
public class ConfigBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String newName;
	private ZonedDateTime lastUpdate;
	private String checkPassword;
	
	public String updateNameUser() {
		if(lastUpdate == null) {
			throw new WebExceptions("login for updating: date for the last update is null");
		}
		FacesContext faceContext = FacesContext.getCurrentInstance();
		try {
			LoginBean loginBean = (LoginBean) faceContext.getExternalContext().getSessionMap().get("loginBean");			
			User userLogged = loginBean.getUserLogged();
			if(!ArgonInstance.matchPassword(checkPassword, userLogged.getPassword())) 
				throw new WebExceptions("wrong password");
			UsersServices.updateName(newName, userLogged.getId(), lastUpdate);
			userLogged.setName(newName);
			faceContext.addMessage(null, new FacesMessage
					(FacesMessage.FACES_MESSAGES, "Name has updated"));
		}catch(Exception e) {
			faceContext.addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
			// Armazena a mensagem no Flash Scope para preservar após redirecionamento
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		return "/logged/config.xhtml?faces-redirect=true";
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	public ZonedDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(ZonedDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}
