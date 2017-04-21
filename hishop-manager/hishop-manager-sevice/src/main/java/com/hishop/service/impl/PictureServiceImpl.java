package com.hishop.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.ResultMap;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hishop.common.util.FtpUtil;
import com.hishop.common.util.IDUtils;
import com.hishop.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService{
	Map resultMap = new HashMap<>();
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;
	@Override
	public Map uploadPicture(MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		
		try {
			String oldName = multipartFile.getOriginalFilename();
			//UUID.randomUUID();
			String newname = IDUtils.genImageName();
			newname = newname + oldName.substring(oldName.lastIndexOf("."));
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result= FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, 
					imagePath, newname, multipartFile.getInputStream());
			
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传失败");
				return resultMap;
			}
				resultMap.put("error", 0);
				resultMap.put("url", IMAGE_BASE_URL+imagePath+"/"+newname);
				return resultMap;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传异常");
			return resultMap;
			
		}
		
	}

}
