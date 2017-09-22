package com.kyunam.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

	private static final String UPLOADED_FOLDER = "/uploads";

	@PostMapping("/image")
	public ResponseEntity<?> handleImageUpload(@RequestParam("file") MultipartFile file) {
		System.out.println("hi");
		String fileName;
		try {
			if (file.isEmpty()) {
				return new ResponseEntity("please select a file!", HttpStatus.OK);
			}
			fileName = saveUploadedFiles(file);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.ok().body(fileName);

	}

	// save file
	private String saveUploadedFiles(MultipartFile file) throws IOException {
		System.out.println("file upload start");
		System.out.println( );
		if (file.isEmpty()) {
			System.out.println("file not found");
		}
		byte[] bytes = file.getBytes();
		String randFileName =  UUID.randomUUID() + file.getOriginalFilename();
		Path path = Paths.get(System.getProperty("user.dir")+"/src/main/resources/static/" + UPLOADED_FOLDER +"/" + randFileName);
		String FilePath = Files.write(path, bytes).toString();
		
		System.out.println("file write success");
		return randFileName;
	}
}
