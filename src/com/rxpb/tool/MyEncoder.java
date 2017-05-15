package com.rxpb.tool;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MyEncoder {
	public String EncoderByMd5(String str) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		// 确定计算方法
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}

	public String getMd5(String str) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.reset();
		md.update(str.getBytes());

		byte[] bArray = md.digest();

		String finalStr = new String(encodeHex(bArray, hexDigits));

		// System.out.println("16位大写：" + finalStr.substring(8, 24));
		// System.out.println("32位大写：" + finalStr);
		return finalStr;
	}

	protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
		final int len = data.length;
		final char[] out = new char[len << 1];
		for (int i = 0, j = 0; i < len; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}
}
