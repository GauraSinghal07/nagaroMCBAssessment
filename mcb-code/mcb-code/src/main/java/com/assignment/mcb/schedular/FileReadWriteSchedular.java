
package com.assignment.mcb.schedular;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.assignment.mcb.model.FileDetails;
import com.assignment.mcb.service.impl.FileStorageServiceImpl;

@Component
public class FileReadWriteSchedular {

	@Autowired
	FileStorageServiceImpl FileStorageService;

	@Scheduled(cron = "0 * * * * ?")
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Java cron job expression:: " + strDate);
		List<FileDetails> findAllByStatus = FileStorageService.getAllFilesByStatus("C");
		FileStorageService.saveFileInFileSystem(findAllByStatus);

	}
}
