package org.nader.io.controllers;

import javax.validation.Valid;
import org.nader.io.entities.Youser;
import org.nader.io.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * Generates a User object for Register Form binding
	 */
	@ModelAttribute
	public Youser generateUser(){
		return new Youser();
	}

    /**
     * Shows registration form to the user
     * @return
     */
	@RequestMapping
	public String showRegister(){
		return("register");
	}
	
	/**
	 * Saves User object which is read from Register Form to the data base.
	 * @Valid will tigger hibernate validation and if input is valid it will be saved 
	 * @param youser
	 */
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("youser") Youser youser, BindingResult result, RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			return "register";
		}
		userService.save(youser);
		redirectAttributes.addFlashAttribute("success", true);
		return("redirect:/register");
	}
	
	/**
	 * If a user name is already taken returns true
	 * @param username
	 */
	@RequestMapping("/alreadyTaken")
	@ResponseBody
	public String available(@RequestParam String username){
		Boolean available = userService.findOneWithName(username) == null;
		return available.toString();
	}
}
