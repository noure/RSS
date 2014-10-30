package com.ensa.jpa.service;

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

import org.springframework.stereotype.Service;

import com.ensa.jpa.entity.Item;
import com.ensa.jpa.exception.RssException;
import com.ensa.jpa.rss.ObjectFactory;
import com.ensa.jpa.rss.TRss;
import com.ensa.jpa.rss.TRssChannel;
import com.ensa.jpa.rss.TRssItem;

@Service
public class RssService {
	
	public List<Item> getItems(File file) throws RssException {
		return getItems(new StreamSource(file));
	}
	
	public List<Item> getItems(String url) throws RssException {
		return getItems(new StreamSource(url));
	}
	private List<Item> getItems(Source source) throws RssException {
		List<Item> items = new ArrayList<Item>();
		try {
			JAXBContext jaxbContext = JAXBContext
					.newInstance(ObjectFactory.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source,
					TRss.class);
			TRss rss = jaxbElement.getValue();
			List<TRssChannel> channels = rss.getChannel();
			for (TRssChannel channel : channels) {
				List<TRssItem> rssItems = channel.getItem();

				for (TRssItem rssItem : rssItems) {
					Item item = new Item();
					item.setTitle(rssItem.getTitle());
					item.setLink(rssItem.getLink());
					item.setDescription(rssItem.getDescription());
					Date publishDate = new SimpleDateFormat(
							"EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH)
							.parse(rssItem.getPubDate());
					item.setPublishDate(publishDate);

					items.add(item);
				}

			}

		} catch (JAXBException e) {
			throw new RssException(e);

		} catch (ParseException e) {
			throw new RssException(e);

		}
		return items;

	}

}
