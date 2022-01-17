package com.justrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.justrap.model.UserInterface;
import com.justrap.service.AudioService;
import com.justrap.service.LoginService;
import com.justrap.service.MusicService;
import com.justrap.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MusicService musicService;
	
	@Autowired
	private AudioService audioService;
	
	@RequestMapping("/")
	public ModelAndView index() {
		loginForTest();
		/*List<Music> musics = musicService.getMusicsByName("toto");
		File audioFile = musicService.getFile(musics.get(0));
		audioService.play(audioFile);*/
		
		
		ModelAndView m = new ModelAndView("index");
		m.addObject("title", "Raplay");
		m.addObject("css", "style.css");
		return m;
	}
	
	// only for test
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public ModelAndView test(@RequestParam("file") MultipartFile file) {
		audioService.stop();
		/*try {
			musicService.createMusicByFile(file, true, "toto", userService.getCurrentUser());
		} catch (InvalidExtensionException e) {
			System.out.println(e.getMessage());
		}*/
		
		ModelAndView m = new ModelAndView("index");
		m.addObject("title", "Raplay");
		m.addObject("css", "style.css");
		return m;
	}
	
	// only for test
	public void loginForTest() {
		UserInterface user = null;
		try {
			user = loginService.getUserByCredentials("toto@gmail.com", "tototo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Utilisateur : " + user.getUsername());
		userService.signIn(user);
	}
	
}
