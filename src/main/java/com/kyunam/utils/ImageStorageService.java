package com.kyunam.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public class ImageStorageService implements StorageService{

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void store(MultipartFile file){
		// TODO Auto-generated method stub
		if (file.isEmpty()) {
			System.out.println("file not found Error");
			return;
		}
		try {
			byte[] bytes = file.getBytes();
			Path savePath = Paths.get(System.getProperty("user.dir") 
					+ "/src/main/resources/static/" + UPLOADED_FOLDER  
					+ "/" + file.getOriginalFilename());
			Files.write(savePath, bytes);
			System.out.println("file write success");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Path load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
