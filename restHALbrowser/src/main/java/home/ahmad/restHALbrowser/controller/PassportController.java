package home.ahmad.restHALbrowser.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import home.ahmad.restHALbrowser.model.Passport;
import home.ahmad.restHALbrowser.repository.PassportRepository;

@RestController
public class PassportController {
	
	private static final String VIEW_INDEX = "index";
	@Autowired
	PassportRepository repo;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView modelAndView){
	    List<Passport> passports = repo.findAll();
	    for(Passport passport : passports){
	        if(passport.getIssueDate() == null){
	            passport.setIssueDate(LocalDate.now()); // Oder einen anderen sinnvollen Wert setzen
	        }
	        if(passport.getExpiryDate() == null){
	            passport.setExpiryDate(LocalDate.now().plusYears(10)); // Beispielhafter Wert
	        }
	    }
	    modelAndView.setViewName(VIEW_INDEX);
	    modelAndView.addObject("pass", passports);
	    return modelAndView;
	}

}