package com.powerhouse.interview.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	public List<String> readLines(MultipartFile file) throws IOException {

		if (file.getSize() > Long.MAX_VALUE - 1) {
			throw new IOException(
					"file size is bigger then maximum array size!");
		}
		
		List<String> list = new ArrayList<String>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
		
		String line;
		while((line = br.readLine()) != null) {				
			list.add(line);
		}
		br.close();

		return list;
	}
	
}
