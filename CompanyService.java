package com.gl.caseStudy3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.caseStudy3.bean.CompanyShare;
import com.gl.caseStudy3.repository.CompanyRepository;


@Service
public class CompanyService {
	@Autowired
	private CompanyRepository repository;
	
	public void save(CompanyShare company) {
		repository.save(company);
	}
	public List<CompanyShare> findAll(){
		
		return repository.findAll();
		
	}
	public CompanyShare findById(Long Id) {

		return repository.getById(Id);
		
	}
	public Long genrateCompanyId() {
		Long val=repository.findMaxCompanyId();
		if (val==null) {
			val=(long) 1000001;
		}
		else
			val=val+1;
		return val;
	}
	public void delete(Long Id) {
		repository.deleteById(Id);;
	}
}
