package com.ezen.project.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.project.model.Report;
import com.ezen.project.service.ReportService;


@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportService svc;
	@Autowired
	ResourceLoader resourceLoader;
	
	@GetMapping("/reportForm")
	public String postForm(Model model) {
		model.addAttribute("report",new Report());
		return "thymeleaf/report/reportForm";
	}
	
	@PostMapping("/sendReportInfo")
	public String sendPostInfo(@Valid Report report,BindingResult result,HttpServletRequest request,Model model,HttpSession session,@RequestParam("files")MultipartFile[] mfiles) {
		if(result.hasErrors()) return "thymeleaf/report/reportForm";
		report.setWriter((String)session.getAttribute("memberEmail"));
		Long saved = svc.sendReportInfo(report);

		String path = "src/main/resources/static";
		File file = new File(path);
		String absolutePath = file.getAbsolutePath();

		boolean fileSaved = svc.fileSave(mfiles,absolutePath,saved);
		
		return "thymeleaf/main/index";
	}
	
	@GetMapping("/show/all/list")
	public String reportList(Model model) {
		List<Report> list = svc.getList();
		model.addAttribute("list",list);
		return "thymeleaf/report/adminOnly/reportList";
	}
	
	@GetMapping("/show/detail/{num}")
	public String reportDetail(@PathVariable("num")Long num,Model model,HttpServletRequest request) {
		String savePath = request.getServletContext().getRealPath("/WEB-INF/post_images");
		Report report = svc.getDetail(num,savePath);
		List<String> imgList = svc.getImgs(num);
		model.addAttribute("imgList",imgList);
		model.addAttribute("report",report);
		return "thymeleaf/report/adminOnly/reportDetail";
	}
	
	@GetMapping("/delete/{num}")
	public String deleteReport(@PathVariable("num")Long num,Model model,HttpServletRequest request) {
		boolean deleted= svc.deleteReport(num);
		if(deleted) {
			return "redirect:/report/show/all/list";
		}
		return "thymeleaf/errorPage/error_404";
	}
	
	//신고 제보자의 신고 리스트 보여주기
	@GetMapping("/user/report/{member}")
	public String userReport(@PathVariable("member")String member,Model model) {
		List<Report> userReports = svc.getUserReport(member);
		model.addAttribute("list",userReports);
		return "thymeleaf/report/adminOnly/reportList";
	}
	
	//신고 당한 사람 관리 창으로 이동
	@GetMapping("/user/controll/{member}")
	public String userControll(@PathVariable("member")String member,Model model) {
		int reportedCnt = svc.reportedCnt(member);
		model.addAttribute("reportedCnt",reportedCnt);
		model.addAttribute("member",member);
		return "thymeleaf/report/adminOnly/userControll";
	}
	
	//경고_메시지_보내기
	@GetMapping("/user/controll/msg/{member}")
	public String userConMsg(@PathVariable("member")String member,Model model) {
		String url="/msg/msgForm/"+member;
		return "redirect:"+url;
	}
	
}
