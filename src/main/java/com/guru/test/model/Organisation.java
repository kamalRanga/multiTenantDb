package com.guru.test.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kamal
 */
@Document
public class Organisation {

	@Id
	private String id;

	private String orgName;

	private String dbName;

	private Date createDtae;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getCreateDtae() {
		return createDtae;
	}

	public void setCreateDtae(Date createDtae) {
		this.createDtae = createDtae;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
