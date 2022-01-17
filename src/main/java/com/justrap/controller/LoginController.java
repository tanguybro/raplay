package com.justrap.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.justrap.model.UserInterface;
import com.justrap.service.AudioService;
import com.justrap.service.LoginService;
import com.justrap.service.UserService;


@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private AudioService audioService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		audioService.stop();
		ModelAndView m = new ModelAndView("login");
		m.addObject("title", "Connexion");
		m.addObject("css", "connexionStyle.css");
		return m;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginRequest(HttpServletRequest request,
			   HttpServletResponse response) {
		String email = request.getParameter("inputEmail");  
	    String password = request.getParameter("inputPassword");
	    ModelAndView model = new ModelAndView("login");
	    UserInterface user = null;
		try {
			user = loginService.getUserByCredentials(email, password); //"toto@gmail.com", "tototo"
			System.out.println(user);
			if(user != null) {
				userService.signIn(user);
				model.setViewName("upload");
				model.addObject("title", "Upload");
				model.addObject("css", "uploadStyle.css");
				System.out.println(userService.getCurrentUser().getUsername());
			}
			else {
				model.addObject("title", "Connexion");
				model.addObject("css", "connexionStyle.css");
				model.addObject("errorMessage", "Identifiant/Mot de passe incorrect");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
