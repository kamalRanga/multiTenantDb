package com.uno.controller;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.uno.helper.ServiceMessage;
import com.uno.helper.ServiceResult;
import com.uno.helper.ServiceResult.StatusCode;
import com.uno.helper.TenantContext;
import com.uno.model.Role;
import com.uno.model.Tenant;
import com.uno.model.TenantDb;
import com.uno.model.TenantLogin;
import com.uno.repository.common.LoginRepository;
import com.uno.service.CommonService;
import com.uno.service.EmailService;
import com.uno.service.LoginService;
import com.uno.service.TemplateService;
import com.uno.service.TenantService;
import com.uno.util.UNOUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "tenantCreate", description = "Sign Up process ")
public class TenantController {

	/**
	 * Instance of Logger {@link Logger}
	 */
	private static final Logger logger = LogManager.getLogger(TenantController.class);

	@Autowired
	private TenantService tenantService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private MongoProperties properties;

	@Autowired
	private TemplateService templateService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private EmailService emailService;

	@ApiOperation(value = "Add a User as a Tenant")
	@RequestMapping(value = "/signUp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object createTenant(@Valid @RequestBody Tenant tenantReq, BindingResult result, String emailTemplate) {
		logger.info("Inside TenantController in createTenant ::::::::::::");
		if (result.hasErrors()) {
			return new ServiceResult(StatusCode.BAD_REQUEST,
					UNOUtils.getServiceMessage(messageSource, result.getAllErrors()), null);
		}
		Tenant tenantObj = commonService.addTenant(tenantReq);
		if (tenantObj.getId() == null)
			return new ServiceResult(StatusCode.UNAUTHORIZED, new ServiceMessage(messageSource, "msg.tenant.fail"),
					false);
		TenantDb tenantDbSaveObj = tenantDBCreation(tenantObj);
		if (tenantDbSaveObj.getId() == null)
			return new ServiceResult(StatusCode.UNAUTHORIZED, new ServiceMessage(messageSource, "msg.tenantDb.fail"),
					false);

		tenantReq.setDbCredsId(tenantDbSaveObj.getId());
		tenantReq.setTenantid(tenantObj.getId());
		tenantObj = commonService.addTenant(tenantReq);

		if (tenantObj.getId() == null)
			return new ServiceResult(StatusCode.UNAUTHORIZED, new ServiceMessage(messageSource, "msg.update.fail"),
					false);

		TenantLogin tenantLoginSaveObj = loginDetailSave(tenantObj, tenantDbSaveObj);
		String emailSubject = "";
		if (tenantLoginSaveObj.getId() != null)
			// emailService.sendEmail(emailSubject);
			return new ServiceResult(StatusCode.SUCCESS, new ServiceMessage(messageSource, "msg.update.success"), true);
		return new ServiceResult(StatusCode.UNAUTHORIZED, new ServiceMessage(messageSource, "msg.update.fail"), false);

	}

	@ApiOperation(value = "Login a User as a Tenant")
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object login(@Valid @RequestBody TenantLogin tenantLogin) {
		// Switch database according to orgname
		TenantDb tenantDb = commonService.getTenanatDbDetails(tenantLogin.getOrgName());
		switchDb(tenantDb);
		//RequestContextHolder.getRequestAttributes().setAttribute("tenantId", tenantDb, RequestAttributes.SCOPE_REQUEST);
		//TenantContext.setCurrentTenant(tenantDb);
		tenantLogin = loginService.login(tenantLogin);
		if (tenantLogin == null) {
			return new ServiceResult(StatusCode.INCORRECT_PASSWORD,
					new ServiceMessage(messageSource, "user.credentials.incorrect"), false);
		}
		return new ServiceResult(StatusCode.SUCCESS, new ServiceMessage(messageSource, "user.login.success"), true);
	}
	
	@ApiOperation(value = "User as a Tenant")
	@RequestMapping(value = "/testApi", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object testApi() {
		
		return new ServiceResult(StatusCode.SUCCESS, new ServiceMessage(messageSource, "user.login.success"), true);
	}

	/**
	 * Insert Tenant login details in tenant DB With Role SUPERADMIN
	 * 
	 * @param tenantObj
	 * @param tenantDbSaveObj
	 * @return
	 */
	private TenantLogin loginDetailSave(Tenant tenantObj, TenantDb tenantDb) {
		List<Role> templateRole = templateService.getAll();
		switchDb(tenantDb);

		TenantLogin tenantLogin = new TenantLogin();
		tenantLogin.setUsername(tenantObj.getEmail());
		tenantLogin.setPassword(UNOUtils.generateRandomString(new Random(), tenantObj.getSubdomain_chosen(), 8));
		tenantLogin.setRoleName(templateService.getRole("SUPERADMIN").getRoleName());
		TenantLogin tenantLoginSaveObj = loginService.add(tenantLogin);
		loginService.addRole(templateRole);
		return tenantLoginSaveObj;
	}

	/**
	 * We switch db according to tenant
	 * 
	 * @param tenantDbSaveObj
	 */
	private void switchDb(TenantDb tenantDb) {
		RequestContextHolder.getRequestAttributes().setAttribute("tenantId", tenantDb, RequestAttributes.SCOPE_REQUEST);
	}

	/**
	 * Insert tenant db details in Master DB
	 * 
	 * @param tenantObj
	 * @return
	 */
	private TenantDb tenantDBCreation(Tenant tenantObj) {
		TenantDb tenantDb = new TenantDb();
		tenantDb.setDbName(tenantObj.getSubdomain_chosen() + "_db"); // Need to discuss
		tenantDb.setDbUsername(tenantObj.getSubdomain_chosen() + "_admin"); // need to discuss
		tenantDb.setDbPassword(UNOUtils.generateRandomString(new Random(), tenantObj.getSubdomain_chosen(), 8));
		tenantDb.setDbHost(properties.getHost());
		tenantDb.setDbPort(properties.getPort());
		tenantDb.setOrgName(tenantObj.getOrgName());

		TenantDb tenantDbSaveObj = commonService.addCreds(tenantDb);

		return tenantDbSaveObj;
	}

}
