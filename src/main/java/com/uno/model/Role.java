package com.uno.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by kamal
 */
@Document
public class Role {

	@Id
	private String id;
	@NotEmpty
	@Indexed(unique = true)
	@ApiModelProperty(notes = "ROLE Unique ", required = true)
	private String roleName;

	private String description;

	public Role() {
	}

	public Role(String id, String roleName, String description) {
		this.id = id;
		this.roleName = roleName;
		this.description = description;
	}

	public Role(String role, String desc) {
		roleName = role;
		description = desc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
