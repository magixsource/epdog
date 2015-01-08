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
		// ����MD5�㷨����MessageDigest����
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] srcBytes = info.getBytes();
		// ʹ��srcBytes����ժҪ
		md5.update(srcBytes);
		// ��ɹ�ϣ���㣬�õ�result
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
