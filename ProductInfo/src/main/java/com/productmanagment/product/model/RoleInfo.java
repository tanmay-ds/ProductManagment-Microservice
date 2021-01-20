package com.productmanagment.product.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class RoleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String role;

	public RoleInfo() {
	}

	public RoleInfo(String role) {
		this.role = role;
	}

}
