package com.home.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import jakarta.faces.context.FacesContext;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * 
 * @author Ahmad Alrefai
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Language {

	@Autowired
	private MessageSource messageSource;

	private Locale locale = Locale.ENGLISH;

	Map<String, Locale> languages = new HashMap<String, Locale>();

	private String localeCode;

//	public Language() {
//		super();
//		languages.put("fr", Locale.ENGLISH);
//		languages.put("en", new Locale("en"));
//		this.localeCode = "en";
//		this.locale = new Locale("en");
//		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
//	}

	public String getMessage(String property) {
        return messageSource.getMessage(property, null, locale);
    }

	public void countryLocaleCodeChanged(String language) {
		for (Map.Entry<String, Locale> entry : languages.entrySet()) {
			if (entry.getValue().toString().equals(language)) {
				this.locale = entry.getValue();
				this.localeCode = entry.getKey();
				FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
			}
		}
	}

	public Map<String, Locale> getLanguages() {
		return languages;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

}
