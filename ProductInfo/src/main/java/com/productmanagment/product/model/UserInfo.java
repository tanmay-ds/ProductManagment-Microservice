package com.productmanagment.product.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;


public class UserInfo {

	private Long uuid;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String password;
	private List<RoleInfo> roles = new ArrayList<>();

	public UserInfo() {
	}

	public UserInfo(Long uuid, @NotNull String firstName, @NotNull String lastName, @NotNull String email,
			@NotNull String phoneNumber, @NotNull String address, List<RoleInfo> roles) {
		super();
		this.uuid = uuid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.password = "admin";
		this.roles = roles;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleInfo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleInfo> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInfo other = (UserInfo) obj;

		return address.equals(other.address) && email.equals(other.email) && firstName.equals(other.firstName)
				&& lastName.equals(other.lastName) && password.equals(other.password)
				&& phoneNumber.equals(other.phoneNumber) && roles.equals(other.roles);
	}

}