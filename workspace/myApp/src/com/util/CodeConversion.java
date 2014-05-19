package com.util;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;

public class CodeConversion {
	public static String utf8ToUnicode(String inStr) {
		char[] myBuffer = inStr.toCharArray();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < inStr.length(); i++) {
			UnicodeBlock ub = UnicodeBlock.of(myBuffer[i]);
			if (ub == UnicodeBlock.BASIC_LATIN) {
				// 英文及数字等
				sb.append(myBuffer[i]);
			} else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				// 全角半角字符
				int j = (int) myBuffer[i] - 65248;
				sb.append((char) j);
			} else {
				// 汉字
				char s = myBuffer[i];
				System.out.println(s);
				String hexS = Integer.toHexString(s);
				System.out.println(hexS);
				String unicode = "\\u" + hexS;
				sb.append(unicode.toLowerCase());
			}
		}
		return sb.toString();
	}

	 public static String unicodeToUtf8(String theString) {
		  char aChar;
		  int len = theString.length();
		  StringBuffer outBuffer = new StringBuffer(len);
		  for (int x = 0; x < len;) {
		   aChar = theString.charAt(x++);
		   if (aChar == '\\') {
		    aChar = theString.charAt(x++);
		    if (aChar == 'u') {
		     // Read the xxxx
		     int value = 0;
		     for (int i = 0; i < 4; i++) {
		      aChar = theString.charAt(x++);
		      switch (aChar) {
		      case '0':
		      case '1':
		      case '2':
		      case '3':
		      case '4':
		      case '5':
		      case '6':
		      case '7':
		      case '8':
		      case '9':
		       value = (value << 4) + aChar - '0';
		       break;
		      case 'a':
		      case 'b':
		      case 'c':
		      case 'd':
		      case 'e':
		      case 'f':
		       value = (value << 4) + 10 + aChar - 'a';
		       break;
		      case 'A':
		      case 'B':
		      case 'C':
		      case 'D':
		      case 'E':
		      case 'F':
		       value = (value << 4) + 10 + aChar - 'A';
		       break;
		      default:
		       throw new IllegalArgumentException(
		         "Malformed   \\uxxxx   encoding.");
		      }
		     }
		     outBuffer.append((char) value);
		    } else {
		     if (aChar == 't')
		      aChar = '\t';
		     else if (aChar == 'r')
		      aChar = '\r';
		     else if (aChar == 'n')
		      aChar = '\n';
		     else if (aChar == 'f')
		      aChar = '\f';
		     outBuffer.append(aChar);
		    }
		   } else
		    outBuffer.append(aChar);
		  }
		  return outBuffer.toString();
		 }
	public static void main(String[] args) {
		String str = utf8ToUnicode("4S在线-中国最独家、最贴心的APP");
		System.out.println(str);
		String strtwo=unicodeToUtf8(str);
		System.out.println(strtwo);
	}
}
