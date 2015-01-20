package com.pramati.training.webcrawler;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MyWebCrawler {
	public static int counter = 10;
	public static String baseURL = "http://mail-archives.apache.org/mod_mbox/maven-users/"; 
	public static void main(String[] args) {
		processPage(baseURL);
	}

	public static void processPage(String URL) {
		Document doc;
		try {
			if(counter > 0) {
				counter--;
			} else {
				return;
			}
			doc = Jsoup.connect(URL).get();
			System.out.println(doc.text());

			// get all links and recursively call the processPage method
			Elements questions = doc.select("a[href]");
			for (Element link : questions) {
				if (link.attr("href").contains("2014"))
					processPage(link.attr("abs:href"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
