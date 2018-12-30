package org.nader.io.controllers;

import org.nader.io.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class AdminController {

	@Autowired
	private UserService userService;
	
	/**
	 * Returns list of all users
	 * @param model
	 */
	@RequestMapping
	public String users(Model model){
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	
	/**
	 * Returns blogs of user with some id
	 * @param model
	 * @param id
	 */
	@RequestMapping("/{id}")
	public String userDetail(Model model, @PathVariable int id){
		model.addAttribute("user", userService.findOneWithBlogs(id));
		return "userDetail";
	}
	
	/**
	 * Deletes a user with some id 
	 * @param id
	 */
	@RequestMapping("/remove/{id}")
	public String removeUser(@PathVariable int id){
		userService.delete(id);
		return "redirect:/users";
	}
}
