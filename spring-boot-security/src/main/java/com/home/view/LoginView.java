package com.home.view;

import java.io.IOException;
import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@ViewScoped
public class LoginView implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2341506243067702836L;
	private String email;
    private String password;

    
    
    @PostConstruct
    public void init() {
        System.out.println("LoginBean wurde initialisiert.");
        
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
//    public String login() {
//        System.out.println("login() wurde aufgerufen!");
//       
//        try {
//            User user = userService.findUserByEmail(email);
//            if (user != null && user.getPassword().equals(password)) {
//                // Bei erfolgreichem Login zur index-Seite umleiten
//                return "index?faces-redirect=true";
//            } else {
//                // Bei fehlgeschlagenem Login eine Fehlermeldung anzeigen
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login fehlgeschlagen", "Ungültige E-Mail oder Passwort."));
//                return null; // Auf der Login-Seite bleiben
//            }
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login-Fehler", "Ein Fehler ist während des Logins aufgetreten."));
//            return null; // Auf der Login-Seite bleiben
//        }
//    }
    
    public String login() {
    	System.out.println("login() wurde aufgerufen!");
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest())
                .getRequestDispatcher("/j_spring_security_check");
        
        try {
            dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
