package com.wuxianedu.utils;

import java.io.File;
import java.util.UUID;

public class FileUtil {
	
	public static String generateFilename(String uploadPath, String filename) {
		int hashCode = filename.hashCode();
		int dir1 = hashCode & 0xF;
		int dir2 = (hashCode >> 4) & 0xF;
		uploadPath = uploadPath + "/" + dir1 + "/" + dir2;
		File path =  new File(uploadPath);
		if(!path.exists()){
			path.mkdirs();
		}
		return filename = uploadPath + "/" + filename;
	}
	public static String getFileUUId(String filename){
		return UUID.randomUUID().toString()+"_"+filename;
	}
}
