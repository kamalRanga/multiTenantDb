package com.uno.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by kamal
 */

@Document(collection = "tenantdetails")
public class Tenant {

	@Id
	@ApiModelProperty(notes = "The database generated tenant ID")
	private String id;
	@Transient
	@NotEmpty
	@Indexed(unique = true)
	@ApiModelProperty(notes = "Email address for a user", required = true)
	private String email;

	@NotEmpty
	@Indexed(unique = true)
	@ApiModelProperty(notes = "User Unique Domain", required = true)
	private String subdomain_chosen;

	private String tenantid;

	@NotEmpty
	@ApiModelProperty(notes = "User First name", required = true)
	private String firstName;

	@ApiModelProperty(notes = "User Last name", required = true)
	private String lastName;

	private String dbCredsId;

	@NotEmpty
	@ApiModelProperty(notes = "Tenant Org name", required = true)
	private String orgName;
	@Transient
	private String location;
	@Transient
	private boolean termCondition;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubdomain_chosen() {
		return subdomain_chosen;
	}

	public void setSubdomain_chosen(String subdomain_chosen) {
		this.subdomain_chosen = subdomain_chosen;
	}

	public String getTenantid() {
		return tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDbCredsId() {
		return dbCredsId;
	}

	public void setDbCredsId(String dbCredsId) {
		this.dbCredsId = dbCredsId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isTermCondition() {
		return termCondition;
	}

	public void setTermCondition(boolean termCondition) {
		this.termCondition = termCondition;
	}

}
