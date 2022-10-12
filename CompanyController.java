package com.gl.caseStudy3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.gl.caseStudy3.bean.CompanyShare;
import com.gl.caseStudy3.service.CompanyService;



@RestController
public class CompanyController {
	@Autowired
	private CompanyService service;
	
	@GetMapping("/index")
	public ModelAndView showIndexpage() {

		ModelAndView mv=new ModelAndView("index");
		List<CompanyShare> companyList=service.findAll();
		mv.addObject("companyRecord",companyList);
		return mv;
	}

	@GetMapping("/company-entry")
	public ModelAndView showCompanyEntrypage() {
		CompanyShare company =new CompanyShare();
		ModelAndView mv=new ModelAndView("companyEntryPage");
		mv.addObject("companyRecord",company);
		return mv;
	}
	
	@PostMapping("/company")
	public ModelAndView saveNewCompany(@ModelAttribute("companyRecord") CompanyShare company) {
		long id=service.genrateCompanyId();
		System.out.println(id);
		company.setCompanyId(id);
		service.save(company);	
		return new ModelAndView("redirect:/index");
	}
//	
//	@GetMapping("/course")
//	public ModelAndView showAllCourses() {
//		List<Course> courseList=service.findAll();
//		ModelAndView mv=new ModelAndView("courseReportPage");
//		mv.addObject("courseList",courseList);
//		return mv;
//		
//	}
//	@GetMapping("/find-course")
//	public ModelAndView shoFindCourse() {
//		return new ModelAndView("findCourse");
//	}
//	@PostMapping("/find-course")
//	public ModelAndView showCourse(@RequestParam("id") long id) {
//		Course course=service.findById(id);
//		if (course==null) {
//			String message="Data not found for the given ID";
//			ModelAndView mv=new ModelAndView("errorShow") ;
//			mv.addObject ("errorMessage" ,message) ;
//			return mv;
//		}
//		List<Course> courseList=new ArrayList<>();
//		courseList.add(course);
//		ModelAndView mv=new ModelAndView("courseReportPage");
//		mv.addObject("courseList",courseList);
//		return mv;
//		
//	}
//	
//	@GetMapping("/display-course/{id}")
//	public ModelAndView showACourses(@PathVariable long id) {
//		Course course=service.findById(id);	
//		ModelAndView mv= new ModelAndView("courseReportPage");
//		mv.addObject("course",course);
//		return mv;
//		
//	}
	
	@GetMapping("/delete-company/{id}")
	public ModelAndView deleteACompany(@PathVariable long id) {
		service.delete(id);	
		return new ModelAndView("redirect:/index");	
	}
	
	@GetMapping("/edit-company/{id}")
	public ModelAndView showCompanyEditPage(@PathVariable long id) {
		CompanyShare company =service.findById(id);
		ModelAndView mv=new ModelAndView("companyEditPage");
		mv.addObject("companyRecord",company);
		return mv;
	}
	@PostMapping("/edit-company/company-edit")
	public ModelAndView editCompany(@ModelAttribute("companyRecord") CompanyShare company) {
	
		service.save(company);	
		return new ModelAndView("redirect:/index");
		
	}
}
