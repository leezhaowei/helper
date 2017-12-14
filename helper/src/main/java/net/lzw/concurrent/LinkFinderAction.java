package net.lzw.concurrent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

public class LinkFinderAction extends RecursiveAction {

	private static final long serialVersionUID = -120927769966307850L;

	private static final long t0 = System.nanoTime();

	private String url;
	private LinkHandler handler;

	public LinkFinderAction(String url, LinkHandler handler) {
		super();
		this.url = url;
		this.handler = handler;
	}

	@Override
	protected void compute() {
		getSimpleLinks(url);
	}

	private void getSimpleLinks(String url) {
		if (handler.visited(url)) {
			return;
		}
		try {
			List<RecursiveAction> actions = new ArrayList<>();
			URL uriLink = new URL(url);
			Parser parser = new Parser(uriLink.openConnection());
			NodeList list = parser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));

			for (int idx = 0; idx < list.size(); idx++) {
				LinkTag linkTag = (LinkTag) list.elementAt(idx);
				String link = linkTag.extractLink();
				if (!link.isEmpty() && !handler.visited(link)) {
					actions.add(new LinkFinderAction(link, handler));
				}
			}

			if (handler.size() == 1500) {
				System.out.println("Time to visit 1500 distinct links = " + (System.nanoTime() - t0));
			}

			invokeAll(actions);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
