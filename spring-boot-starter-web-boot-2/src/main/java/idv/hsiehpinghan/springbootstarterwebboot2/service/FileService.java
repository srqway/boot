package idv.hsiehpinghan.springbootstarterwebboot2.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	private final Path ROOT_PATH = Paths.get("/tmp");

	public void store(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		if (file.isEmpty()) {
			throw new IOException(String.format("file(%) is empty !!!", fileName));
		}
		if (fileName.contains("..")) {
			throw new IOException(String.format("fileName(%) with relative path !!!", fileName));
		}

		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, ROOT_PATH.resolve(fileName));
		}
	}

	public void update(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		if (file.isEmpty()) {
			throw new IOException(String.format("file(%) is empty !!!", fileName));
		}
		if (fileName.contains("..")) {
			throw new IOException(String.format("fileName(%) with relative path !!!", fileName));
		}
		try (InputStream inputStream = file.getInputStream()) {
			Files.copy(inputStream, ROOT_PATH.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	public Resource load(String fileName) throws IOException {
		Path file = ROOT_PATH.resolve(fileName);
		Resource resource = new UrlResource(file.toUri());
		if ((resource.exists() == false) || (resource.isReadable() == false)) {
			return null;
		}
		return resource;
	}

	public void delete(String fileName) throws IOException {
		Files.delete(ROOT_PATH.resolve(fileName));
	}

	public boolean existsAndReadable(String fileName) throws IOException {
		Path file = ROOT_PATH.resolve(fileName);
		Resource resource = new UrlResource(file.toUri());
		return (resource.exists() == true) && (resource.isReadable() == true);
	}
}