package com.zwli.security;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * <p>
 * 因为任意一个字符串，都是由若干字节表示的，每个字节实质就是一个有8位的进进制数，
 * 又因为一个8位二进制数，可用两位16进制字符串表示。因此任意一个字符串可以由两位16进制字符串表示。
 * 而DES是对8位二进制数进行加密，解密。所以用DES加密解密时，可以把加密所得的8位进进制数，转成两位16进制数进行保存，传输。<br>
 * <br>
 * <b>另：一个字符串加密后所得的8位二进制数，通常不再时字符串了，如果 直接把这种密文所得的8位二进制数强制转成字符串，有许多信息因为异
 * 常而丢失，导制解密失败。因制要把这个8位二制数，直接以数的形式 保存下来，而通常是用两位十六进制数表示。</b><br>
 * <br>
 * 具体方法：<br>
 * 1. 把一个字符串转成8位二进制数，用DES加密，得到8位二进制数的密文；<br>
 * 2. 然后把（由1）所得的密文转成两位十六进制字符串；<br>
 * 3. 解密时，把（由2）所得的两位十六进制字符串，转换成8位二进制 数的密文；<br>
 * 4. 把（由3）所得的密文，用DES进行解密，得到8位二进制数形式的明文，并强制转换成字符串。<br>
 * </p>
 */
public class DESEncrypt {

	private static final String T = "0";
	private static final String DES = "DES";
	private Key key;
	private Cipher cipherEnc;
	private Cipher cipherDec;

	public DESEncrypt(String key) throws Exception {
		cipherEnc = Cipher.getInstance(DES);
		cipherDec = Cipher.getInstance(DES);
		generateKey(key);
	}

	/**
	 * 生成加密密钥
	 * 
	 * @param key
	 */
	private void generateKey(String key) throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance(DES);
		generator.init(new SecureRandom(key.getBytes()));
		// 生成密钥
		this.key = generator.generateKey();

		// 初始化加密解密工具
		cipherEnc.init(Cipher.ENCRYPT_MODE, this.key);
		cipherDec.init(Cipher.DECRYPT_MODE, this.key);

		generator = null;
	}

	/**
	 * 加密
	 * 
	 * @param src
	 *            源数据
	 * @return 加密后数据
	 */
	public String encrypt(String src) throws Exception {
		return byte2hex(encrypt(src.getBytes()));
	}

	/**
	 * 解密
	 * 
	 * @param encryptedSrc
	 *            密文
	 * @return 明文
	 */
	public String decrypt(String encryptedSrc) throws Exception {
		return new String(decrypt(hex2byte(encryptedSrc.getBytes())));
	}

	/**
	 * 加密以byte[]明文输入,byte[]密文输出
	 * 
	 * @param bytes
	 * @return
	 */
	private byte[] encrypt(byte[] bytes) throws Exception {
		// Cipher cipher = Cipher.getInstance(DES);
		// cipher.init(Cipher.ENCRYPT_MODE, key);
		// return cipher.doFinal(bytes);
		return cipherEnc.doFinal(bytes);
	}

	/**
	 * 解密以byte[]密文输入,以byte[]明文输出
	 * 
	 * @param bytes
	 * @return
	 */
	private byte[] decrypt(byte[] bytes) throws Exception {
		// Cipher cipher = Cipher.getInstance(DES);
		// cipher.init(Cipher.DECRYPT_MODE, key);
		// return cipher.doFinal(bytes);
		return cipherDec.doFinal(bytes);
	}

	/**
	 * 二行制转字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) { // 一个字节的数，
		// 转成16进制字符串
		String stmp = "";
		StringBuilder info = new StringBuilder();
		final int N = 0XFF;
		for (byte element : b) {
			// 整数转成十六进制表示
			stmp = (Integer.toHexString(element & N));
			if (stmp.length() == 1) {
				info.append(T).append(stmp);
			} else {
				info.append(stmp);
			}
		}
		return info.toString().toUpperCase();
	}

	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("Argument is not even.");
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static void main(String[] args) {
		long a = System.currentTimeMillis();
		String src = "096debab87e68ed1095cf9c128260515<?xml version=\"1.0\" encoding=\"utf-8\"?><message><header><agentID>3022</agentID><timestamp>2011-12-14 19:27:37</timestamp><tradeType>1001</tradeType></header><body><lotteryRequest><agentOrders><agentOrder ID=\"3022201112141000928975\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"10.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889331\" orderTimes=\"5\" payMoney=\"10.0\" playMethod=\"101\" >6,2,1</orderItem></agentOrder><agentOrder ID=\"3022201112141000928978\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889334\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >5,5,4</orderItem></agentOrder><agentOrder ID=\"3022201112141000928979\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889335\" orderTimes=\"1\" payMoney=\"4.0\" playMethod=\"102\" >68,4,2</orderItem></agentOrder><agentOrder ID=\"3022201112141000928987\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"6.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889343\" orderTimes=\"3\" payMoney=\"6.0\" playMethod=\"101\" >7,8,1</orderItem></agentOrder><agentOrder ID=\"3022201112141000928988\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889344\" orderTimes=\"2\" payMoney=\"4.0\" playMethod=\"801\" >6,7,8</orderItem></agentOrder><agentOrder ID=\"3022201112141000928989\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889345\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >6,1,8</orderItem></agentOrder><agentOrder ID=\"3022201112141000928990\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889346\" orderTimes=\"2\" payMoney=\"4.0\" playMethod=\"101\" >8,8,7</orderItem></agentOrder><agentOrder ID=\"3022201112141000929147\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889503\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >4,6,3</orderItem></agentOrder><agentOrder ID=\"3022201112141000929148\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889504\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >2,5,6</orderItem></agentOrder><agentOrder ID=\"3022201112141000929149\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889505\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"801\" >2,3,8</orderItem></agentOrder><agentOrder ID=\"3022201112141000929150\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889506\" orderTimes=\"1\" payMoney=\"4.0\" playMethod=\"101\" >2,8,6;6,4,6</orderItem></agentOrder><agentOrder ID=\"3022201112141000929152\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"10.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889508\" orderTimes=\"5\" payMoney=\"10.0\" playMethod=\"101\" >2,1,9</orderItem></agentOrder><agentOrder ID=\"3022201112141000929153\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"2.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889509\" orderTimes=\"1\" payMoney=\"2.0\" playMethod=\"101\" >3,0,8</orderItem></agentOrder><agentOrder ID=\"3022201112141000929154\" lotteryID=\"3d\" issueID=\"11341\" orderPayMoney=\"4.0\" userIdentitycard=\"110108196605095815\" userRealname=\"13901325750\" userPhone=\"13901325750\" isProject=\"\" projectID=\"\" projectTicketCount=\"\" ><orderItem ID=\"10889510\" orderTimes=\"2\" payMoney=\"4.0\" playMethod=\"101\" >3,6,5</orderItem></agentOrder></agentOrders></lotteryRequest></body></message>";
		src = "这世上最难了解的是爱、给人压力最大的是爱、造成最多问题的是爱、影响最深的是爱。凡是爱得过多，多到可以把孩子溺死的，都是「溺爱」。其实每种动物都会爱，甚至在孩子小的时候，都会溺爱。我卧室窗前有棵石楠树，到了春天，常有知更鸟在上面筑巢孵蛋，我总看见鸟爸鸟妈，各站在巢的一边，叼着满口的小虫，争着往鸟娃娃的嘴里塞，吃不下了还硬塞。妙的是，牠们塞归塞，可能有一天，又带着「大鱼大肉」回家，却发现孩子全不见了。这时候只见大鸟站在巢边发愣，不知牠心里是什么滋味。小鸟飞了，因为翅膀硬了，不再需要父母溺爱。";
		String key = "000000";

		try {
			DESEncrypt des = new DESEncrypt(key);

			System.out.println("src=\t" + src);
			String encSrc = des.encrypt(src);
			System.out.println("encSrc=" + encSrc);

			String decSrc = des.decrypt(encSrc);
			System.out.println("decSrc=\t" + decSrc);
			long b = System.currentTimeMillis() - a;
			System.out.println("time=" + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
