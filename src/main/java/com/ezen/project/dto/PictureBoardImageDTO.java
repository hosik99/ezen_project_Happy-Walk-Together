package com.ezen.project.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PictureBoardImageDTO {
	private String uuid;
	private String imgName;
	private String path;
	
	public String getImageURL() {
		try {
			return URLEncoder.encode(path + "/" + uuid + "_" + imgName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getThumbnailURL() {
		try {
			return URLEncoder.encode(path + "/s_" + uuid + "_" + imgName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
