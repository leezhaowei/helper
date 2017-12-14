package net.lzw.concurrent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class LinkFinder implements Runnable {

	private static final long t0 = System.nanoTime();

	private String url;
	private LinkHandler handler;

	public LinkFinder(String url, LinkHandler handler) {
		super();
		this.url = url;
		this.handler = handler;
	}

	@Override
	public void run() {
		getSimpleLinks(url);
	}

	private void getSimpleLinks(String url) {
		if (handler.visited(url)) {
			return;
		}

		try {
			URL uriLink = new URL(url);
			Parser parser = new Parser(uriLink.openConnection());
			NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
			List<String> urls = new ArrayList<>();

			for (int idx = 0; idx < list.size(); idx++) {
				LinkTag linkTag = (LinkTag) list.elementAt(idx);
				String link = linkTag.extractLink();
				if (!link.isEmpty() && !handler.visited(link)) {
					urls.add(link);
				}
			}

			if (handler.size() == 1500) {
				System.out.println("Time to visit 1500 distinct links = " + (System.nanoTime() - t0));
			}

			for (String s : urls) {
				handler.queueLink(s);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
