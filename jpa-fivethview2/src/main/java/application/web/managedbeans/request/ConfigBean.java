package application.web.managedbeans.request;

import java.io.IOException;
import java.io.Serializable;
import java.time.ZonedDateTime;

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
	
	public void updateNameUser() {
		System.out.println(lastUpdate);
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
			System.out.println("Names has changed");
		}catch(Exception e) {
			try {
				faceContext.getExternalContext().redirect("/login.xhtml");
			}catch (IOException e1) {
				throw new WebExceptions("error for redirect: \n" + e1.getMessage());
			}
			throw new WebExceptions("login not found: \n" + e.getMessage());
		}
		
		//boolean match = ArgonInstance.matchPassword(password, userFound.getPassword());
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
