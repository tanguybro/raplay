package com.justrap.manager.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.justrap.manager.FileManager;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;

@Component
public class FileManagerImpl implements FileManager {
	
	@Resource
	private List<String> audioFileExtensions;
	
	@Resource
	private String musicsPath;
	
	@Override
	public void storeFile(MultipartFile file, int fileId) {
		try {
			file.transferTo(new File(musicsPath + "/" + fileId + "." + getExtension(file)));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public File getFile(String fileName) {
		return new File(musicsPath + "/" + fileName);
	}
	
	@Override
	public boolean audioFileHasValidExtension(MultipartFile audioFile) {
		return audioFileExtensions.contains(getExtension(audioFile));
	}

	@Override
	public String getExtension(MultipartFile file) {
		return FilenameUtils.getExtension(file.getOriginalFilename());
	}
	
	@Override
	public String getExtension(File file) {
		return FilenameUtils.getExtension(file.getName());
	}
	
	@Override
	public int getDuration(File audioFile) {
		Header h = null;
		FileInputStream inputFile = null;
		try {
			inputFile = new FileInputStream(audioFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Bitstream bitstream = new Bitstream(inputFile);
	    try {
	        h = bitstream.readFrame();

	    } catch (BitstreamException e) {
	        e.printStackTrace();
	    }
	    int size = h.calculate_framesize();
	    h.ms_per_frame();
	    h.max_number_of_frames(10000);
	    h.total_ms(size);

	    long tn = audioFile.length();
	    h.min_number_of_frames(500);
	    return (int) (h.total_ms((int) tn)/1000);
	}

}
