package org.nader.io.service;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.nader.io.entities.Item;
import org.nader.io.exception.RssException;
import org.nader.io.services.RSSservice;

public class RSSserviceTest {

	private RSSservice rssService;
	
	@Before
	public void setUp() throws Exception {
		 rssService = new RSSservice();
	}

	@Test
	public void testGetItemsFile() throws RssException {
		List<Item> items = rssService.getItems(new File("testData.xml"));
		assertEquals(10, items.size());
		Item firstItem = items.get(0);
		assertEquals("How to solve Source not found error during debug in Eclipse", firstItem.getTitle());
		assertEquals("23 06 2014 01:05:49", new SimpleDateFormat("dd MM yyyy HH:mm:ss").format(firstItem.getPublishedDate()));
	}

}
