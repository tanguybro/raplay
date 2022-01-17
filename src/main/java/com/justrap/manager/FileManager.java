package com.justrap.manager;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileManager {
	void storeFile(MultipartFile file, int fileId);
	File getFile(String fileName);
	boolean audioFileHasValidExtension(MultipartFile audioFile);
	String getExtension(MultipartFile file);
	String getExtension(File file);
	int getDuration(File file);
}

