package com.assignment.mcb.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.assignment.mcb.dto.ResponseMessage;
import com.assignment.mcb.dto.UploadFileResponse;
import com.assignment.mcb.enums.DocumentType;
import com.assignment.mcb.model.FileDetails;
import com.assignment.mcb.service.impl.FileStorageServiceImpl;

@RestController
public class FileController {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileStorageServiceImpl storageService;

	/*
	 * @Autowired private FileReadWriteSchedular schedular;
	 */

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("documentType") DocumentType documentType) {
		String message = "";
		try {
			org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();

			storageService.store(file, documentType, user.getUsername());
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/files")
	public ResponseEntity<List<UploadFileResponse>> getListFiles() {
		List<UploadFileResponse> files = storageService.getAllFiles().map(dbFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
					.path(dbFile.getFileId()).toUriString();
			return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, dbFile.getData().length);
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/files/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable String id) {
		FileDetails fileDB = storageService.getFile(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
				.body(fileDB.getData());
	}

	@GetMapping("/data/{status}")
	public ResponseEntity<byte[]> getFilesByStatus(@PathVariable("status") String status) {
		System.out.println("**");
		List<FileDetails> findAllByStatus = storageService.getAllFilesByStatus("C");
		storageService.saveFileInFileSystem(findAllByStatus);
		/*
		 * List<FileDetails> fileDB = storageService.getAllFilesByStatus(status);
		 * 
		 * String path = System.getProperty("user.home") + java.io.File.separator +
		 * "Documents"; path += java.io.File.separator + "mcbTestData"; try {
		 * 
		 * java.io.File file = new java.io.File(path); OutputStream os = new
		 * FileOutputStream(file); for (FileDetails s : fileDB) { //
		 * out.write(s.getData().toString()); os.write(s.getData());
		 * 
		 * } os.close();
		 */
		return null;

//	  return ResponseEntity.ok() .header(HttpHeaders.CONTENT_DISPOSITION,
//	  "attachment; filename=\"" + fileDB.getFileName() + "\"")
//	  .body(fileDB.getData());

	}
}
