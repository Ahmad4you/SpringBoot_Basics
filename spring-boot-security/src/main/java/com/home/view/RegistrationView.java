package com.home.view;


import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.home.model.User;
import com.home.service.UserService;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
/**
 * 
 * @author Ahmad Alrefai
 */


@Component
@ViewScoped
public class RegistrationView implements Serializable {
	
	private static final long serialVersionUID = 4271324810000334620L;
	@Autowired
    private UserService userService;
	private User user = null;
	
	public RegistrationView(){
		super();
		user = new User();
	}
	
	@Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
	
	
	// Methode zum Registrieren des Benutzers
    public void userRegistration() {
        try {
            userService.saveUser(user);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration Successful", "User has been registered."));
            // Leeren das Formular nach erfolgreicher Registrierung
            user = new User();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration Failed", "An error occurred while registering the user."));
        }
    }


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
