package com.zwli.util;

import java.io.UnsupportedEncodingException;

/**
 * @author 李赵伟 Create: 4:59:29 PM May 11, 2009
 */
class CharsetUtil {

	public static void main(String args[]) {
		a();
		b();
	}

	static void b() {
		String s1 = "我是中国人";
		String s2 = "imchinese";
		String s3 = "im中国人";
		System.out.println(s1 + ":" + s1.length());
		System.out.println(s2 + ":" + s2.length());
		System.out.println(s3 + ":" + s3.length());

		System.out.println("++++++++++++++++++++++++++++++++++++");
		System.out.println((s1.getBytes().length == s1.length()) ? "s1无汉字"
				: "s1有汉字");
		System.out.println((s2.getBytes().length == s2.length()) ? "s2无汉字"
				: "s2有汉字");
		System.out.println((s3.getBytes().length == s3.length()) ? "s3无汉字"
				: "s3有汉字");
	}

	static void a() {
		try {
			String s = new String("李赵伟".getBytes("big5"), "iso-8859-1");
			System.out.println(s);
			System.out.println(isBIG5(s));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断一个ISO8859-1字符串是否采用BIG5字符集
	 * 
	 * @param line
	 * @return
	 */
	public static int isBIG5(String line) {
		int count = 0;
		for (int i = 0; i < line.length() - 1; i++) {
			if (isBIG5((byte) line.charAt(i), (byte) line.charAt(i + 1))) {
				count++;
				i++;// 跳过一个字符
			}
		}
		return count;
	}

	// 判断一个ISO8859-1字符串是否采用GB2312字符集
	public static int isGB2312(String line) {
		int count = 0;
		for (int i = 0; i < line.length() - 1; i++) {
			if (isGB2312((byte) line.charAt(i), (byte) line.charAt(i + 1))) {
				count++;
				i++;// 跳过一个字符
			}
		}
		return count;
	}

	// 判断一个ISO8859-1字符串是否采用GBK字符集
	public static int isGBK(String line) {
		int count = 0;
		for (int i = 0; i < line.length() - 1; i++) {
			if (isGBK((byte) line.charAt(i), (byte) line.charAt(i + 1))) {
				count++;
				i++;// 跳过一个字符
			}
		}
		return count;
	}

	// 判断一个ISO8859-1字符串是否采用GB18030字符集
	public static int isGB18030(String line) {
		int count = 0;
		for (int i = 0; i < line.length() - 1; i++) {
			if (isGB18030((byte) line.charAt(i), (byte) line.charAt(i + 1))) {
				count++;
				i++;// 跳过一个字符
			}
		}
		return count;
	}

	/**
	 * 判断是否为GBK字符
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	public static boolean isGBK(byte head, byte tail) {
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return ((iHead >= 0x81 && iHead <= 0xfe) && (iTail >= 0x40 && iTail <= 0xfe));
	}

	/**
	 * 判断是否为GB2312字符
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	public static boolean isGB2312(byte head, byte tail) {
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return (iHead >= 0xb0 && iHead <= 0xf7)
				&& (iTail >= 0xa0 && iTail <= 0xfe);
	}

	/**
	 * 判断是否为GB18030字符
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	public static boolean isGB18030(byte head, byte tail) {
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return (iHead >= 0x81 && iHead <= 0xFE && ((iTail >= 0x40 && iTail <= 0x7E) || (iTail >= 0x80 && iTail <= 0xFE)));
	}

	/**
	 * 判断是否为BIG5字符
	 * 
	 * @param head
	 * @param tail
	 * @return
	 */
	public static boolean isBIG5(byte head, byte tail) {
		int iHead = head & 0xff;
		int iTail = tail & 0xff;
		return ((iHead >= 0x81 && iHead <= 0xfe) && ((iTail >= 0x40 && iTail <= 0x7e) || (iTail >= 0xa1 && iTail <= 0xfe)));
	}
}
