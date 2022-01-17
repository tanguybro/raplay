package com.justrap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.justrap.service.AudioService;
import com.justrap.service.UserService;

@Controller
public class UploadController {
	
	@Autowired
	private AudioService audioService;

	@RequestMapping("/upload")
	public ModelAndView upload() {
		audioService.stop();
		ModelAndView m = new ModelAndView("upload");
		m.addObject("title", "Upload");
		m.addObject("css", "uploadStyle.css");
		return m;
	}
	
}
