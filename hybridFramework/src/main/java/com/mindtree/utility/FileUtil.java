package com.mindtree.utility;

public class FileUtil {

	
	public static String getCanonicalPath() {
		String strResult = "";
		try {
			strResult = new java.io.File(".").getCanonicalPath();
		}catch(Exception e) {
			
		}
		return strResult;
	}
	
	
}
