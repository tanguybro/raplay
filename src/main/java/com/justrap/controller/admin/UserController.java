package com.justrap.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.justrap.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,
			   HttpServletResponse response) {
		ModelAndView model = new ModelAndView("crud/user/index");
		model.addObject("title", "All Users");
		model.addObject("css", "CRUDStyle.css");
		
		String pattern = request.getParameter("inputSearch");
		if(pattern == null) {
			model.addObject("usersList", userService.getUsersList());
			System.out.println(userService.getUsersList().size());
		}
		else {
			model.addObject("usersList", userService.getFilteredUsers(pattern));
		}
		return model;
	}
	
	@RequestMapping(value = "/show")
	public ModelAndView show() {
		return null;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUserRequest(HttpServletRequest request,
			   HttpServletResponse response) {
		String username = request.getParameter("inputUsername");
	    String mail = request.getParameter("inputEmail");
	    String password = request.getParameter("inputPassword");
	    //System.out.println(username+mail+password);
	    userService.createUser(username, password, mail);
	    
	    return new ModelAndView("redirect:/user/");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ModelAndView editUserRequest(HttpServletRequest request,
			   HttpServletResponse response) {
		String id = request.getParameter("id");
		String username = request.getParameter("inputUsername");
		//On ne peut pas changer l'email je crois
		//String changeMail = request.getParameter("emailCheckBox");
	    String changePassword = request.getParameter("passwordCheckBox");
	    
	    userService.updateUsernameUser(id, username);
	    if(changePassword == "checked") {
	    	try {
				FirebaseAuth.getInstance().generatePasswordResetLink(FirebaseAuth.getInstance().getUser(id).getEmail());
				// Il faut trouver un moyen de mettre à jour la BD
				//Peut etre avant chaque connexion ?
	    	} catch (FirebaseAuthException e) {
				e.printStackTrace();
			}
	    }

		return new ModelAndView("redirect:/user/");
	}
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ModelAndView deleteUserRequest(HttpServletRequest request,
			   HttpServletResponse response) {
		
		String id = request.getParameter("id");
		userService.deleteUser(id);
		
		return new ModelAndView("redirect:/user/");
	}

}
