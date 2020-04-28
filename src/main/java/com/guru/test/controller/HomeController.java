package com.guru.test.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.guru.test.model.Organisation;
import com.guru.test.service.OrgService;

@RestController
public class HomeController {

	@Autowired
	private OrgService orgService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void index(@RequestParam(required = false) String db) {
        // save tentant into shared master db ---
		// Inject tenantId in the request
		RequestContextHolder.getRequestAttributes().setAttribute("tenantId", db, RequestAttributes.SCOPE_REQUEST);

		Organisation org = new Organisation();
		org.setOrgName(db);
		org.setDbName(db);
		org.setCreateDtae(new Date());

		orgService.addOrg(org);
	}

	@GetMapping(value = "/getOrg/{id}/{db}")
	public Object activateUser(@PathVariable("id") String id, @PathVariable("db") String db) {
		
		// Inject tenantId in the request
		RequestContextHolder.getRequestAttributes().setAttribute("tenantId", db, RequestAttributes.SCOPE_REQUEST);

		return orgService.getOrg(id);
	}

}
