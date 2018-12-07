package idv.hsiehpinghan.springrestdocsmockmvcboot.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import idv.hsiehpinghan.springrestdocsmockmvcboot.criteria.FileStoreCriteria;
import idv.hsiehpinghan.springrestdocsmockmvcboot.service.FileService;

@RestController
@RequestMapping("/api/1.0/files")
public class FileController {
	private FileService fileService;

	public FileController(FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@PostMapping(value = "/")
	public ResponseEntity<?> store(FileStoreCriteria criteria, UriComponentsBuilder uriComponentsBuilder)
			throws Exception {
		MultipartFile file = criteria.getFile();
		String fileName = file.getOriginalFilename();
		if (fileService.existsAndReadable(fileName) == true) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		fileService.store(file);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/api/files/{fileName}").buildAndExpand(fileName).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping(value = "/")
	public ResponseEntity<?> update(@RequestPart("file") MultipartFile file, UriComponentsBuilder uriComponentsBuilder)
			throws Exception {
		String fileName = file.getOriginalFilename();
		if (fileService.existsAndReadable(fileName) == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		fileService.update(file);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("/api/files/{fileName}").buildAndExpand(fileName).toUri());
		return new ResponseEntity<>(headers, HttpStatus.OK);
	}

	@GetMapping(value = "/{fileName:.+}")
	public ResponseEntity<Resource> load(@PathVariable("fileName") String fileName) throws Exception {
		Resource resource = fileService.load(fileName);
		if (resource == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{fileName:.+}")
	public ResponseEntity<?> delete(@PathVariable("fileName") String fileName) throws Exception {
		if (fileService.existsAndReadable(fileName) == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		fileService.delete(fileName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
