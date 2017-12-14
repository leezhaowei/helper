package net.lzw.concurrent;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebCrawler6 implements LinkHandler {

	private final Collection<String> visitedLinks = Collections.synchronizedSet(new HashSet<>());
	private String url;
	private ExecutorService execService;

	public WebCrawler6(String url, int maxThreads) {
		super();
		this.url = url;
		this.execService = Executors.newFixedThreadPool(maxThreads);
	}

	@Override
	public void queueLink(String link) throws Exception {
		startNewThread(link);
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

	private void startNewThread(String link) throws Exception {
		execService.execute(new LinkFinder(link, this));
	}

	private void startCrawlin() throws Exception {
		startNewThread(this.url);
	}

	public static void main(String[] args) throws Exception {
		new WebCrawler6("http://www.javaworld.com", 24).startCrawlin();
	}

}
