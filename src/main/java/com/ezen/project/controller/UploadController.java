package com.ezen.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.project.dto.UploadResultDTO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Slf4j
@RequestMapping("/upd")
public class UploadController {
	
	@Autowired
	private ServletContext sctx;
	
	List<UploadResultDTO> resultDTOList = new ArrayList<>();

	@PostMapping("/uploadAjax")
	public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
		String fileSavePath = sctx.getRealPath("/WEB-INF/upload_images");
		
		for(MultipartFile uploadFile : uploadFiles) {
			//이미지 파일만 업로드 가능
			if(uploadFile.getContentType().startsWith("image") == false) {
				log.warn("this file is not image type");
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
				uploadFile.transferTo(savePath); //실제 이미지 저장
				
				//섬네일 생성
				String thumbnailSaveName = fileSavePath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
				//섬네일 파일 이름은 중간에 s_로 시작하도록
				File thumbnailFile = new File(thumbnailSaveName);
				//섬네일 생성
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);
				resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));
			}	catch (IOException e) {
				e.printStackTrace();
			}
		}//end for
		return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
	}
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName, String size) {
		String fileSavePath = sctx.getRealPath("/WEB-INF/upload_images");
		ResponseEntity<byte[]> result = null;
		
		try {
			String srcFileName = URLDecoder.decode(fileName, "UTF-8");
			log.info("fileName: {}", srcFileName);
			File file = new File(fileSavePath + File.separator + srcFileName);
			if(size != null && size.equals("1")) {
				file = new File(file.getParent(), file.getName().substring(2));
			}
			log.info("file: {}", file);
			
			HttpHeaders header = new HttpHeaders();
			
			//MIME타입 처리
			header.add("Content-Type", Files.probeContentType(file.toPath()));
			//파일 데이터 처리
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch(Exception e) {
			log.error("{}", e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
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
	
	@PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){
		String fileSavePath = sctx.getRealPath("/WEB-INF/upload_images");
        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            File file = new File(fileSavePath +File.separator+ srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());

            result = thumbnail.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
