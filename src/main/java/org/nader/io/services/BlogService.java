package org.nader.io.services;

import java.util.List;
import org.nader.io.entities.Blog;
import org.nader.io.entities.Item;
import org.nader.io.entities.Youser;
import org.nader.io.exception.RssException;
import org.nader.io.repositories.BlogRepository;
import org.nader.io.repositories.ItemRepository;
import org.nader.io.repositories.YouserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private YouserRepository youserRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private RSSservice rssService;

	/**
	 * reads blog from url and retrieves and saves items to database 
	 * @param blog
	 */
	public void saveItems(Blog blog){
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink()); //check if the items is already saved
				if(savedItem == null){
					item.setBlog(blog);
					itemRepository.save(item);
				}
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Every hour reloads the saved blogs
	 * one hour = 60 seconds * 60 minutes * 1000 milli seconds
	 */
	@Scheduled(fixedDelay = 3600000)
	public void reloadBlogs(){
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			saveItems(blog);
		}
	}
	
	/**
	 * saves a blog for a user
	 * @param blog
	 * @param name
	 */
	public void save(Blog blog, String name) {
		Youser youser = youserRepository.findByName(name);
		blog.setYouser(youser);
		blogRepository.save(blog);
		saveItems(blog);
	}

	/**
	 * deletes a blog 
	 * @param blog
	 */
	@PreAuthorize("#blog.youser.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);		
	}

	/**
	 * returns a blog with some id
	 * @param id
	 */
	public Blog findOne(int id) {
		return blogRepository.getOne(id);
	}
}
