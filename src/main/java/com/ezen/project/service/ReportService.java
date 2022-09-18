package com.ezen.project.service;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.project.Repository.CntReportRepository;
import com.ezen.project.Repository.ReportImgRepository;
import com.ezen.project.Repository.ReportRepository;
import com.ezen.project.model.CntReport;
import com.ezen.project.model.Report;
import com.ezen.project.model.ReportImg;

@Service
public class ReportService {

	@Autowired
	private ReportRepository reportRps;
	@Autowired
	private ReportImgRepository imgRps;
	@Autowired
	private CntReportRepository cntRps;
	
	public Long sendReportInfo(Report report) {
		report.setWdate(returnDate());
		Report vo = reportRps.save(report);
		CntReport conReport = new CntReport();
		conReport.setMember(vo.getReportId());
		conReport.setWdate(vo.getWdate());
		conReport.setReason(vo.getReason());
		cntRps.save(conReport);
		
		return vo.getNum();
	}

	public boolean fileSave(MultipartFile[] mfiles, String savePath, Long num) {
		File dir = new File(savePath+"/report_images");
		if(dir.exists()==false) {dir.mkdirs();}
		
		String filePath;
		List<ReportImg> imageList = new ArrayList<>();
		
		// WEB-INF/post_images에 파일 저장
		try {
			for(int i=0;i<mfiles.length;i++) {
				
				String orignName = mfiles[i].getOriginalFilename();	//원래 이름
				int lastIdx = orignName.lastIndexOf("."); 
				String orignName_1 = orignName.substring(0, lastIdx);
				String orignName_2 = orignName.substring(lastIdx);
				
				String fname_changed = orignName_1+System.nanoTime()+orignName_2;
				filePath = savePath+"/report_images"+"/"+fname_changed;
				
				ReportImg image = new ReportImg();
				image.setFname(fname_changed);
				image.setFpath(filePath);
				image.setPnum(num);
				image.setUdate(returnDate());
				
				mfiles[i].transferTo(new File(filePath));
				
				imageList.add(image);
			}
			imgRps.saveAll(imageList);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	private Date returnDate() {
		return Date.valueOf(LocalDate.now());
	}

	public List<Report> getList() {
		List<Report> reportList = reportRps.findAll();
		return reportList;
	}

	public Report getDetail(Long num,String savePath) {
		Optional<Report> report = reportRps.findById(num);
		Report reportVo = report.get();
		if(reportVo.getReaded() == 0) reportRps.readed(reportVo.getNum());
		return reportVo;
	}

	public List<String> getImgs(Long num) {
		return imgRps.findByPnum(num);
	}

	public List<Report> getUserReport(String member) {
		List<Report> userReports = reportRps.findByWriter(member);
		return userReports;
	}

	public boolean deleteReport(Long num) {
		try {
			reportRps.deleteById(num);
			imgRps.deleteByPnum(num);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public int reportedCnt(String member) {
		return cntRps.cntMemReported(member);
	}
	
}
