package com.example.demo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileUtil {
	
	public static void fileUpload(@RequestParam("file") MultipartFile file) {
		try {
			Path path = Paths.get("./src/main/resources/static/image/provisional.jpg");
			Files.write(path, file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void fileUpdate(int number) {
		try {
			Path path1 = Paths.get("./src/main/resources/static/image/provisional.jpg");
			if(Files.exists(path1)) {
				Path path2 = Paths.get("./src/main/resources/static/image/" + number + ".jpg");
				if(Files.exists(path2)) {
					fileDelete(Integer.toString(number));
				}
				Files.copy(path1, path2);
				fileDelete("provisional");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void fileDelete(String s) {
		try {
			Path path = Paths.get("./src/main/resources/static/image/" + s + ".jpg");
			Files.delete(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
