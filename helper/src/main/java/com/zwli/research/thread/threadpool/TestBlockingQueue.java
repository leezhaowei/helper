package com.zwli.research.thread.threadpool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池、阻塞队列
 * 
 * @author 李赵伟 Create: 1:55:17 PM Mar 12, 2008
 */
public class TestBlockingQueue {

	public static void main(String[] args) {
		// Scanner in = new Scanner(System.in);
		// System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src):
		// ");
		// String directory = in.nextLine();
		// System.out.print("Enter keyword (e.g. volatile): ");
		// String keyword = in.nextLine();
		test();
	}

	static final int QUEUE_SIZE = 100; // 队列大小
	static final int THERAD_SIZE = 10; // 线程池大小

	static void test() {
		String dir = "d:/OpenSource/corejava";
		String keyword = "java";

		ExecutorService pool = Executors.newFixedThreadPool(THERAD_SIZE);
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(QUEUE_SIZE);
		FileEnumerationTask fet = new FileEnumerationTask(queue, new File(dir));

		pool.submit(fet);
		pool.submit(new SearchTask(queue, keyword));
		pool.shutdown();

	}
}

/**
 * This task enumerates all files in a directory and its subdirectories.
 */
class FileEnumerationTask implements Runnable {

	public static File DUMMY = new File("");
	private BlockingQueue<File> queue;
	private File startingDirectory;

	/**
	 * Constructs a FileEnumerationTask.
	 * 
	 * @param queue
	 *            the blocking queue to which the enumerated files are added
	 * @param startingDirectory
	 *            the directory in which to start the enumeration
	 */
	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}

	public void run() {
		try {
			enumerate(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Recursively enumerates all files in a given directory and its
	 * subdirectories
	 * 
	 * @param directory
	 *            the directory in which to start
	 */
	public void enumerate(File directory) throws InterruptedException {
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				enumerate(file);
			} else {
				queue.put(file);
			}
		}
	}
}

/**
 * This task searches files for a given keyword.
 */
class SearchTask implements Runnable {

	private BlockingQueue<File> queue;
	private String keyword;

	/**
	 * @param queue
	 *            the queue from which to take files
	 * @param keyword
	 *            the keyword to look for
	 */
	public SearchTask(BlockingQueue<File> queue, String keyword) {
		this.queue = queue;
		this.keyword = keyword;
	}

	public void run() {
		try {
			boolean done = false;
			while (!done) {
				File file = queue.take();
				if (file == FileEnumerationTask.DUMMY) {
					queue.put(file);
					done = true;
				} else {
					search(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Searches a file for a given keyword and prints all matching lines.
	 * 
	 * @param file
	 *            the file to search
	 */
	public void search(File file) throws IOException {
		Scanner in = null;
		try {
			in = new Scanner(new FileInputStream(file));
			int lineNumber = 0;
			while (in.hasNextLine()) {
				lineNumber++;
				String line = in.nextLine();
				if (line.contains(keyword)) {
					System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber,
							line);
				}
			}
		} finally {
			if (null != in) {
				in.close();
			}
		}
	}
}
