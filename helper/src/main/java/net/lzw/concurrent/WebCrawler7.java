package net.lzw.concurrent;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;

public class WebCrawler7 implements LinkHandler {

	private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<>());
	private String url;
	private ForkJoinPool mainPool;

	public WebCrawler7(String url, int maxThreads) {
		super();
		this.url = url;
		this.mainPool = new ForkJoinPool(maxThreads);
	}

	private void startCrawling() {
		mainPool.invoke(new LinkFinderAction(this.url, this));
	}

	@Override
	public void queueLink(String link) throws Exception {

	}

	@Override
	public int size() {
		return visitedLinks.size();
	}

	@Override
	public void addVisited(String link) {
		visitedLinks.add(link);
	}

	@Override
	public boolean visited(String link) {
		return visitedLinks.contains(link);
	}

	public static void main(String[] args) throws Exception {
		new WebCrawler7("http://www.javaworld.com", 24).startCrawling();
	}
}
