package com.akshar.iot.smarthome.webservice.vo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.akshar.iot.smarthome.webservice.vo.UserDeviceVO;

public class UserSwitchDetailVO implements Serializable{

	private int userId;
	private String firstName;
	private String lastName;
	private String phoneNo;
	
	private List<ControlBoxVO> controlBoxList;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public List<ControlBoxVO> getControlBoxList() {
		return controlBoxList;
	}

	public void setControlBoxList(List<ControlBoxVO> controlBoxList) {
		this.controlBoxList = controlBoxList;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
}
