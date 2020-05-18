package com.uno.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uno.model.Tenant;

@Component
public class UserValidator implements Validator {
	private Pattern pattern;    
	 private Matcher matcher;    
	 
	    
	 private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"    
	   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";    
	/**
	 * Instance of Logger {@link Logger}
	 */
	private static final Logger logger = LogManager.getLogger(UserValidator.class);


	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub

	}
	/**
	 * Validate the user details during signup creation
	 * 
	 * @param object
	 * @param err
	 */
	public void userDetailsValidator(Object object, Errors err) {
		
		logger.debug("Inside UserValidator in userDetailsValidator method ::::");

		ValidationUtils.rejectIfEmptyOrWhitespace(err, "fName", "msg.fName.required", "First Name Required ");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "lName", "msg.lName.required", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "emailId", "msg.emailId.required", "Email Id  Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "email", "msg.emailId.required", "Email Id  Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "password", "msg.password.required", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "orgName", "msg.orgName.required", "Organization Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(err, "domainName", "msg.domainName.required", "Domain Name Required");

		Tenant userDTO = (Tenant) object;
		if (!err.hasErrors()) {
			if ((userDTO.getEmail() != null)) {
				if(userDTO.getEmail().contains("gmail")||userDTO.getEmail().contains("yahoo")) {
					err.rejectValue("emailId", "msg.emailId.domain", "Email id Should be organization domain id");
					}
				pattern = Pattern.compile(EMAIL_PATTERN);

				matcher = pattern.matcher(userDTO.getEmail());
				if (!matcher.matches()) {
					err.rejectValue("emailId", "msg.emailId.incorrect", "Enter a correct email");
				}
			}
		}
		 
	}

}
