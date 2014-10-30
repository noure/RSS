package com.ensa.jpa.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ensa.jpa.entity.Item;
import com.ensa.jpa.exception.RssException;

public class RssServiceTest {
	private RssService rssService;

	@Before
	public void setUp() throws Exception {

		rssService = new RssService();
	}

	@Test
	public void test() throws RssException {
		File file = new File("test-rss/javavids.xml");

		List<Item> items = rssService.getItems(file);
		assertEquals(10, items.size());
		Item first = items.get(0);
		assertEquals(
				"How to solve Source not found error during debug in Eclipse",
				first.getTitle());
		assertEquals(
				"proxy.google.com/~r/javavids/~3/CJQ8y5pkbzc/how-to-solve-source-not-found-error-during-debug-in-eclipse.html",
				first.getLink());

	}

}
