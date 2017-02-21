package com.powerhouse.interview.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

public class FileUtilTest {

	@Test (expected=IOException.class)
	public void fileSizeLargerThanMaxLongValue() throws IOException {
		MultipartFile mockedFile = mock(MultipartFile.class);
		when(mockedFile.getSize()).thenReturn(Long.MAX_VALUE);
		
		new FileUtil().readLines(mockedFile);
	}

}
