package org.nader.io.services;


import java.util.List;
import org.nader.io.entities.Item;
import org.nader.io.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
	
	@Autowired ItemRepository itemRepository;

	public List<Item> getItems(){
		return itemRepository.findAll(PageRequest.of(0, 20, Direction.DESC, "publishedDate")).getContent();
	}
}
