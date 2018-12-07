package idv.hsiehpinghan.springrestdocsmockmvcboot.criteria;

import org.springframework.web.multipart.MultipartFile;

public class FileStoreCriteria {
	private MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
