package ahmad.home.loginform;

import jakarta.annotation.PostConstruct;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @author Ahmad Alrefai
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {
    
	private static final long serialVersionUID = -851195577843884608L;
	private String username;
    private String password;
    
    @PostConstruct
    public void init() {
    	System.out.println("bean initialisiert");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String login() {
//        // Hier implementieren Sie Ihre Login-Logik
//        if ("admin".equals(username) && "password".equals(password)) {
//        	System.out.println("success");
//        	
//            return "/index?faces-redirect=true";
//        } else {
//        	System.out.println("failure");
//            return "failure";
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