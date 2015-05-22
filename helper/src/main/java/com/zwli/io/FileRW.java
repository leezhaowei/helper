package com.zwli.io;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 文件读取、写入类<br>
 * 创建日期：2007-10-25
 * 
 * @author 李赵伟
 */
public class FileRW {

	/**
	 * 测试
	 */
	public static void main(String[] args) {
		// String content =
		// "取得坐标的地点：\t天安门,116.398,39.9091;天安门,116.398,39.9091;故宫,116.402,39.9152;天安门,116.398,39.9091;故宫,116.402,39.9152;颐和园,116.285725,39.99835333;";
		// content = content
		// + "\r\n未取得坐标的地点：\tasldkfjlaksdfjlkajsdflkadsjflkjasldkjfl";
		// String fileName = "D:/temp/coors.txt";
		// boolean append = false;
		// write(fileName, content, append);
		String fn = "D:/temp/hehe.gif";
		fn = "D:/temp/music.mp3";
		byte buf[];
		try {
			buf = readForByte(fn);
			out.println(buf.length);

			String fileName = "D:/temp/hehe1.gif";
			fileName = "D:/temp/music1.mp3";
			write(fileName, buf);
		} catch (IOException e) {
			e.printStackTrace();
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
		BufferedWriter bw = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}

			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f, append)));
			bw.write(content);
			bw.flush();
			out.println("写文件完毕：" + fileName);
		} finally {
			if (null != bw) {
				bw.close();
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param fileName
	 * @param buf
	 * @throws IOException
	 */
	public static void write(String fileName, byte buf[]) throws IOException {
		File f = new File(fileName);
		DataOutputStream dos = null;
		try {
			if (!f.exists()) {
				f.createNewFile();
			}

			dos = new DataOutputStream(new FileOutputStream(f));
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
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		File f = new File(fileName);
		if (!f.exists()) {
			err.println("文件：" + fileName + " 不存在！");
		}

		try {
			br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f)));
			String s;
			while (br.ready()) {
				s = br.readLine();
				sb.append(s + "\n");
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
		FileInputStream fis = null;
		File f = new File(fileName);
		if (!f.exists()) {
			err.println("文件：" + fileName + " 不存在！");
		}

		ByteArrayOutputStream baos = null;
		try {
			byte buf[] = new byte[8192];
			fis = new FileInputStream(f);
			baos = new ByteArrayOutputStream();
			int len = fis.read(buf);
			while (-1 != len) {
				baos.write(buf, 0, len);
				len = fis.read(buf);
			}
			buf = baos.toByteArray();
			return buf;
		} finally {
			if (null != fis) {
				fis.close();
			}
			if (null != baos) {
				baos.close();
			}
		}
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
				sb.append(s + "\n");
			}
			return sb.toString();
		} finally {
			if (null != br) {
				br.close();
			}
		}
	}
}
