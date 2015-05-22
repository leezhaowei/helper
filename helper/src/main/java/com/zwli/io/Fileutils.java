package com.zwli.io;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 李赵伟 Create: 2:13:25 PM Apr 3, 2008
 */
public class Fileutils {

	/**
	 * 文件大小，单位：KB
	 * 
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	public static long fileSizeToKB(String fileName) {
		File f = new File(fileName);
		boolean exist = f.exists();
		boolean isfile = f.isFile();
		if (exist && isfile) {
			long fileSize = f.length();
			long fileKB = fileSize / 1024;
			return fileKB;
		} else {
			throw new IllegalArgumentException("文件可能不存在或者是目录！");
		}
	}

	/**
	 * 把内容写道文件
	 * 
	 * @param fileName
	 *            文件名称
	 * @param content
	 *            文件内容
	 * @param append
	 *            true，写入文件末尾；false，覆盖文件
	 * @throws IOException
	 */
	public static void write(String fileName, String content, boolean append)
			throws IOException {
		File f = new File(fileName);
		if (!f.exists()) {
			f.createNewFile();
		}
		write(fileName, content.getBytes(), append);
	}

	/**
	 * 复制文件
	 * 
	 * @param fileName
	 * @param buf
	 * @param append
	 * @throws IOException
	 */
	public static void write(String fileName, byte buf[], boolean append)
			throws IOException {
		File f = new File(fileName);
		if (!f.exists()) {
			f.createNewFile();
		}
		DataOutputStream dos = null;
		try {
			dos = new DataOutputStream(new FileOutputStream(f, append));
			dos.write(buf);
			dos.flush();
			out.println("写文件完毕：" + fileName);
		} finally {
			if (null != dos) {
				dos.close();
			}
		}
	}

	/**
	 * 读取文件
	 * 
	 * @param fileName
	 * @return 文件内容
	 * @throws IOException
	 */
	public static String read(String fileName) throws IOException {
		File f = new File(fileName);
		if (!f.exists()) {
			err.println("文件：" + fileName + " 不存在！");
		}

		return read(new FileInputStream(f));
	}

	/**
	 * 从数据流中读取文件内容
	 * 
	 * @param is
	 * @return 文件内容
	 * @throws IOException
	 */
	public static String read(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			String s;
			while (br.ready()) {
				s = br.readLine();
				if (null != s && 0 != s.trim().length()) {
					sb.append(s.trim() + "\n");
				}
			}
			return sb.toString();
		} finally {
			if (null != br) {
				br.close();
			}
		}
	}

	/**
	 * 读取文件获得字节数组
	 * 
	 * @param fileName
	 * @return 文件内容的字节形式
	 * @throws IOException
	 */
	public static byte[] readForByte(String fileName) throws IOException {
		File f = new File(fileName);
		if (!f.exists()) {
			err.println("文件：" + fileName + " 不存在！");
		}
		return readForByte(new FileInputStream(f));
	}

	/**
	 * 从数据流中获得字节数组
	 * 
	 * @param is
	 * @return 数据流中的字节
	 * @throws IOException
	 */
	public static byte[] readForByte(InputStream is) throws IOException {
		ByteArrayOutputStream baos = null;
		try {
			byte buf[] = new byte[8192];
			baos = new ByteArrayOutputStream();
			int len = is.read(buf);
			while (-1 != len) {
				baos.write(buf, 0, len);
				len = is.read(buf);
			}
			buf = baos.toByteArray();
			return buf;
		} finally {
			if (null != is) {
				is.close();
			}
			if (null != baos) {
				baos.close();
			}
		}
	}

}
