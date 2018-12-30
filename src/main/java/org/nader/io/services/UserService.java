package org.nader.io.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.nader.io.entities.Blog;
import org.nader.io.entities.Item;
import org.nader.io.entities.Role;
import org.nader.io.entities.Youser;
import org.nader.io.repositories.BlogRepository;
import org.nader.io.repositories.ItemRepository;
import org.nader.io.repositories.RoleRepository;
import org.nader.io.repositories.YouserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Transactional
public class UserService {

	@Autowired
	private YouserRepository youserRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RoleRepository roleRepository;
		
	/**
	 * returns sorted list of all users
	 */
	public List<Youser> findAll(){
		Sort sort = Sort.by(Direction.ASC, "name");
		List<Youser> yousers =youserRepository.findAll(sort);
		return yousers;
	}
	
	/**
	 * returns a user with some id
	 * @param id
	 */
	public Youser getOne(int id) {
		return youserRepository.getOne(id);
	}

	/**
	 * returns sorted list of 10 items belonging to the user's blogs 
	 * @param id
	 */
	@Transactional
	public Youser findOneWithBlogs(int id) {
		Youser youser = getOne(id);
		List<Blog> blogs = blogRepository.findByYouser(youser);
		for (Blog blog : blogs) {
			List<Item> items = itemRepository.findByBlog(blog, PageRequest.of(0, 10, Direction.DESC, "publishedDate"));
			blog.setItems(items);
		}
		youser.setBlogs(blogs);
		return youser;
	}
	
	@RequestMapping("/register")
	public String showRegister(){
		return "register";
	}

	/**
	 * saves user and sets his role and encrypted password
	 * @param youser
	 */
	public void save(Youser youser) {
		youser.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		youser.setPassword(encoder.encode(youser.getPassword()));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByName("ROLE_USER"));
		youser.setRoles(roles);
		youserRepository.save(youser);
	}

	public Youser findOneWithBlogs(String name) {
		Youser youser = youserRepository.findByName(name);
		return findOneWithBlogs(youser.getId());
	}

	/**
	 * deletes a user with some id
	 * @param id
	 */
	public void delete(int id) {
		youserRepository.deleteById(id);
	}
	/**
	 * returns one user name
	 * @param username
	 */
	public Youser findOneWithName(String username) {
		return youserRepository.findByName(username);
	}
}
