package com.assignment.mcb.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.mcb.enums.DocumentType;
import com.assignment.mcb.model.File;
import com.assignment.mcb.repository.FileRepository;

@Service
public class FileStorageService {

	@Autowired
	private FileRepository fileRepository;

	public File getFile(String id) {
		return fileRepository.findById(id).get();
	}

	public Stream<File> getAllFiles() {
		return fileRepository.findAll().stream();
	}

	public File store(MultipartFile file, String documentType, String userName) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		DocumentType documentTyp = DocumentType.valueOf(documentType);
		File FileDB = new File(null, fileName, userName, file.getBytes(), documentTyp.name(), file.getSize());
		return fileRepository.save(FileDB);
	}
}
