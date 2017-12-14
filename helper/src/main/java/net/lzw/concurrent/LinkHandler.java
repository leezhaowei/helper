package net.lzw.concurrent;

public interface LinkHandler {

	public void queueLink(String link) throws Exception;

	public int size();

	public void addVisited(String link);

	public boolean visited(String link);

}
