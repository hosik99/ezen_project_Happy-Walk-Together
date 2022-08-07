package com.ezen.project.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/upd")
public class UploadController {
	

	@PostMapping("/uploadAjax")
	public void uploadFile(MultipartFile[] uploadFiles, HttpServletRequest request) {
		ServletContext context = request.getServletContext();
		String fileSavePath = context.getRealPath("/WEB-INF/upload_images");
		
		for(MultipartFile uploadFile : uploadFiles) {
			//이미지 파일만 업로드 가능
			if(uploadFile.getContentType().startsWith("image") == false) {
				log.warn("this file is not image type");
				return;
			}
			
			//실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

			log.info("fileName: " + fileName);
			//날짜 폴더 생성
			String folderPath = makeFolder(fileSavePath);
			
			//UUID
			String uuid = UUID.randomUUID().toString();
			
			//저장한 파일 이름 중간에 "_"를 이용해서 구분
			String saveName = fileSavePath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
			
			Path savePath = Paths.get(saveName);
			
			try { 
				uploadFile.transferTo(savePath);
			}	catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}//end for
	}
	
	private String makeFolder(String fileSavePath) {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		String folderPath = str.replace("/", File.separator);
		
		// make folder --------------
		File uploadPathFolder = new File(fileSavePath, folderPath);
		
		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		return folderPath;
	}
}
