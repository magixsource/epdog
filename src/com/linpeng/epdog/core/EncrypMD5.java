package com.linpeng.epdog.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Basic MD5
 * 
 * @author linpeng
 *
 */
public class EncrypMD5 {
	public static byte[] eccrypt(String info) throws NoSuchAlgorithmException {
		// 根据MD5算法生成MessageDigest对象
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcBytes = info.getBytes();
		// 使用srcBytes更新摘要
		md5.update(srcBytes);
		// 完成哈希计算，得到result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	public static String eccryptString(String info) {
		try {
			byte[] bytes = eccrypt(info);
			StringBuffer buf = new StringBuffer("");
			int i;
			for (int offset = 0; offset < bytes.length; offset++) {
				i = bytes[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			info = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return info;

	}

}
