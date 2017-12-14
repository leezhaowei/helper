package com.zwli.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class CopyFiles {

	public static void main(String[] args) {
		String src = "D:/me/iphone";
		String dest = "D:/me/DEST";
		TYPE[] types = { TYPE.ALL };
		File srcF = new File(src);
		File destDir = new File(dest);

		try {
			CopyFiles cp = new CopyFiles();
			cp.copyFiles(srcF, destDir, types);
			System.out.println("Total files: " + cp.getCount());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int count = 0;

	public static enum TYPE {
		ALL, JPG, PNG,;
	}

	public void copyFiles(File src, File destDir, TYPE[] types) throws IOException {
		if (!src.isDirectory()) {
			System.err.println("no folder ...");
			return;
		}
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		File[] files = src.listFiles();
		for (File f : files) {
			if (f.isFile() && verifyFile(types, f)) {
				count++;
				FileUtils.copyFileToDirectory(f, destDir);
			} else if (f.isDirectory()) {
				copyFiles(f, destDir, types);
			}
		}
	}

	private boolean verifyFile(TYPE[] types, File f) {
		boolean ok = false;
		for (TYPE t : types) {
			if (TYPE.ALL == t) {
				return true;
			}
			String suffix = t.name().toLowerCase();
			ok = f.getAbsolutePath().toLowerCase().endsWith(suffix);
		}
		return ok;
	}

	public int getCount() {
		return count;
	}

}
