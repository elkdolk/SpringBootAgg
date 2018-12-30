package org.nader.io.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	/**
	 * Returns login page
	 */
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
}
