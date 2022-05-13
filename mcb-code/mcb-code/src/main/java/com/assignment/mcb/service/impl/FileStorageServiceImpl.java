package com.assignment.mcb.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.mcb.enums.DocumentType;
import com.assignment.mcb.model.FileDetails;
import com.assignment.mcb.repository.FileRepository;
import com.assignment.mcb.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {
	@Value("${file.upload-dir}")
	private String filePath;

	@Autowired
	private FileRepository fileRepository;

	@Override
	public FileDetails getFile(String id) {
		return fileRepository.findById(id).get();
	}

	@Override
	public Stream<FileDetails> getAllFiles() {
		return fileRepository.findAll().stream();
	}

	@Override
	public List<FileDetails> getAllFilesByStatus(String status) {
		return fileRepository.findAllByStatus(status);
	}

	@Override
	public FileDetails store(MultipartFile file, DocumentType documentType, String userName) throws IOException {
		Long datetime = System.currentTimeMillis();
		Timestamp timestamp = new Timestamp(datetime);
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String[] split = fileName.split("\\.");
		String concat = split[0].concat(datetime.toString()).concat(".").concat(split[1]);

		FileDetails FileDB = new FileDetails(null, concat, userName, file.getBytes(), documentType.toString(),
				file.getSize(), "C", timestamp);
		return fileRepository.save(FileDB);
	}

	@Override
	public void saveFileInFileSystem(List<FileDetails> findAllByStatus) {
		for (FileDetails filedata : findAllByStatus) {
			Path newFilePath = Paths.get(System.getProperty("user.home") + java.io.File.separator + filePath
					+ java.io.File.separator + filedata.getFileName());

			Path newFilePath1 = Paths.get(System.getProperty("user.home") + java.io.File.separator + filePath
					+ java.io.File.separator + "index" + filedata.getFileName());
			Path createFile;
			try {
				createFile = Files.createFile(newFilePath);
				createFile = Files.createFile(newFilePath1);
				String finalData = createFileContent(filedata);
				Files.write(newFilePath, filedata.getData());
				Files.write(newFilePath1, finalData.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public String createFileContent(FileDetails fileData) {
		String comment = "COMMENT: ONDEMAND GENERIC INDEX FILE GENERATED";
		String groupFieldName = "GROUP FIELD NAME : Timestamp";
		String groupFieldValue = "GROUP FIELD VALUE : " + convertTimeStampToFormat(fileData.getTimestamp());
		String groupFieldNameForCus = "GROUP FIELD NAME : CUSTOMER_ID";
		String groupFieldValueForCus = "GROUP FIELD VALUE : " + fileData.getCustomerId();
		String groupFieldNameForDt = "GROUP FIELD NAME : DOCUMENT_TYPE";
		String groupFieldValueForDt = "GROUP FIELD VALUE : " + fileData.getDocumentType();
		String groupFieldNameForFn = "GROUP FIELD NAME : " + "DS" + convertTimeStampToString(fileData.getTimestamp());

		String finalData = comment + "\r\n" + groupFieldName + "\r\n" + groupFieldValue + "\r\n" + groupFieldNameForCus
				+ "\r\n" + groupFieldValueForCus + "\r\n" + groupFieldNameForDt + "\r\n" + groupFieldValueForDt + "\r\n"
				+ groupFieldNameForFn;
		return finalData;
	}

	public String convertTimeStampToString(Timestamp timestamp) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String timestp = dateFormat.format(timestamp);
		return timestp;
	}

	public String convertTimeStampToFormat(Timestamp timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String timestp = dateFormat.format(timestamp);
		return timestp;
	}
}
