package org.nader.io.services;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.nader.io.entities.Item;
import org.nader.io.exception.RssException;
import org.springframework.stereotype.Service;
import generated.ObjectFactory;
import generated.TRss;
import generated.TRssChannel;
import generated.TRssItem;

/**
 * this service class reads xml file from disk or from url and parses it and retrieves items from it
 * and saves items to List of items
 */
@Service
public class RSSservice {

	public List<Item> getItems(File file) throws RssException {

		return getItems(new StreamSource(file));
	}

	public List<Item> getItems(String url) throws RssException {

		return getItems(new StreamSource(url));
	}
	
	private List<Item> getItems(Source source) throws RssException {

		ArrayList<Item> list = new ArrayList<Item>();

		try {
			JAXBContext jaxbcontext = JAXBContext.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbcontext.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source, TRss.class);
			TRss rss = jaxbElement.getValue();

			List<TRssChannel> channels = rss.getChannel();
			for (TRssChannel channel : channels) {
				List<TRssItem> items = channel.getItem();
				for (TRssItem rssItem : items) {
					Item item = new Item();
					item.setTitle(rssItem.getTitle());
					item.setDescription(rssItem.getDescription());
					item.setLink(rssItem.getLink());
					Date pubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
							.parse(rssItem.getPubDate());
					item.setPublishedDate(pubDate);
					list.add(item);
				}
			}
		} catch (JAXBException e) {
			throw new RssException(e);

		} catch (ParseException e) {
			throw new RssException(e);
		}
		return list;
	}
}
