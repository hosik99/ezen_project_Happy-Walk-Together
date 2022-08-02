package com.ezen.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SignUpValidationService {
	
	public ResponseEntity member(BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			StringBuilder stringBuilder = new StringBuilder();
			bindingResult.getAllErrors().forEach(objectError->{
				FieldError fieldError = (FieldError) objectError;
				String errMessage = objectError.getDefaultMessage();
				
				log.info("fieldError : "+fieldError.getField());
			});
		}
		return null;
	}
}
