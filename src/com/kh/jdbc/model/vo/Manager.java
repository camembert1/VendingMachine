package com.kh.jdbc.model.vo;

public class Manager {
	
	private String managerId;
	private String managerPwd;
	private String managerName;
	private String managerPhone;
	
	public Manager() {
		super();
	}
	public Manager(String managerId, String managerPwd, String managerName, String managerPhone) {
		super();
		this.managerId = managerId;
		this.managerPwd = managerPwd;
		this.managerName = managerName;
		this.managerPhone = managerPhone;
	}

	
	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPwd() {
		return managerPwd;
	}

	public void setManagerPwd(String managerPwd) {
		this.managerPwd = managerPwd;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	
	@Override
	public String toString() {
		return "Manager [managerId=" + managerId + ", managerPwd=" + managerPwd + ", managerName=" + managerName
				+ ", managerPhone=" + managerPhone + "]";
	}
	
}
