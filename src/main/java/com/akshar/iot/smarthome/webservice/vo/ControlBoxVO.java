package com.akshar.iot.smarthome.webservice.vo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import com.akshar.iot.smarthome.webservice.vo.UserDeviceVO;

public class ControlBoxVO implements Serializable{

	private int controlBoxId;
	private String controlBoxCode;
	private String controlBoxName;
	private List<UserDeviceVO> userDevices;
	
	public int getControlBoxId() {
		return controlBoxId;
	}
	public void setControlBoxId(int controlBoxId) {
		this.controlBoxId = controlBoxId;
	}
	public String getControlBoxCode() {
		return controlBoxCode;
	}
	public void setControlBoxCode(String controlBoxCode) {
		this.controlBoxCode = controlBoxCode;
	}
	public String getControlBoxName() {
		return controlBoxName;
	}
	public void setControlBoxName(String controlBoxName) {
		this.controlBoxName = controlBoxName;
	}
	public List<UserDeviceVO> getUserDevices() {
		return userDevices;
	}
	public void setUserDevices(List<UserDeviceVO> userDevices) {
		this.userDevices = userDevices;
	}

	
	
}
