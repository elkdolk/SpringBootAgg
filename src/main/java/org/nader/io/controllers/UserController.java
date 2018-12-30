package org.nader.io.controllers;

import java.security.Principal;
import javax.validation.Valid;
import org.nader.io.entities.Blog;
import org.nader.io.services.BlogService;
import org.nader.io.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	/**
	 * Returns help page 
	 */
	@RequestMapping("/help")
	public String help(){
		return "help";
	}
			
	/**
	 * Generates a Blog object for Modal form binding
	 */
	@ModelAttribute
	public Blog generateBlog(){
		return new Blog();
	}
	
	/**
	 * returns user account detail
	 * @param model
	 * @param principal
	 */
	@RequestMapping("/account")
	public String account(Model model, Principal principal){
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "account";
	}
	
	/**
	 * gets name of the user from principal and adds a blog for user 
	 * @Valid triggers hibernate validation and if input is valid it will be saved
	 * @param model
	 * @param blog
	 * @param result
	 * @param principal
	 */
	@RequestMapping(value="/account", method=RequestMethod.POST)
	public String doAddBlog(Model model, @Valid @ModelAttribute("blog") Blog blog, BindingResult result , Principal principal){
		if(result.hasErrors()){
			return account(model, principal);
		}
		String name = principal.getName();
		blogService.save(blog, name);
		return ("redirect:/account");
	}
	
	/**
	 * removes a blog with some id
	 * @param id
	 */
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id ){
		Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account";
	}
}
