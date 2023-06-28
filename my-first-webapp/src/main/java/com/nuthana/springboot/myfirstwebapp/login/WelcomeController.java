package com.nuthana.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController { // Renaming LoginController to WelcomeController At the time of sping Security

	// private Logger logger = LoggerFactory.getLogger(getClass());

	// http://localhost:8080/login?name=nuthana

	// model
//	@RequestMapping("login")
//	public String LoginJsp(@RequestParam String name, ModelMap model) {
//		model.put("name", name);
//		logger.debug("Request param is {}", name);
//		logger.info("I want this printed at info level");
//		logger.warn("I want this printed at warn level");

	// System.out.println("Request param is " + name); // here we are using sysout
	// for loging
	// IT IS NOT RECOMMENDED FOR PROD CODE //so we use logger
//		return "login";

//	private AuthenticationService authenticationService;
//
//	public LoginController(AuthenticationService authenticationService) {
//		super();
//		this.authenticationService = authenticationService;
//	}  //At the time of sping Security

	// login
	// when we have only value that time it is handling both GET and POST request
	// so to handle only get we are adding method =get
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("name", getLoggedInUsername()); // model.put("name", "Nuthana"); here we are hardcoding the name to
													// avoid that we are using spring security
		// with createing a method
		return "welcome";

	}

	private String getLoggedInUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	public String gotoWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
//		if (authenticationService.authenticate(name, password)) {
//			model.put("name", name);
//			// model.put("password", password);
//
//			// Authentication-if give validate data then to welcome or redirect to login
//
//			return "welcome";
//		}
//
//		model.put("errorMessage", "Invalid Credentials! Please try again");
//		return "login";
//	}  // At the time of sping Security
}
