/**
 * 
 */
package com.home.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Ahmad Alrefai
 */
@RestController
public class HWcontroller {
	
	@GetMapping("/hello-ahmad")
	public String hello() {
		return "hello Ahmad";
	}

}
