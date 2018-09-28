package com.akshar.iot.smarthome.webservice.vo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import com.akshar.iot.smarthome.webservice.vo.CommandVO;

public class UserDeviceVO implements Serializable{

	int userDeviceId;
	String userDeviceCode;
	String deviceStatus;
	List<CommandVO> commandList;
	
	public int getUserDeviceId() {
		return userDeviceId;
	}
	public void setUserDeviceId(int userDeviceId) {
		this.userDeviceId = userDeviceId;
	}
	public String getUserDeviceCode() {
		return userDeviceCode;
	}
	public void setUserDeviceCode(String userDeviceCode) {
		this.userDeviceCode = userDeviceCode;
	}
	public String getDeviceStatus() {
		return deviceStatus;
	}
	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}
	public List<CommandVO> getCommandList() {
		return commandList;
	}
	public void setCommandList(List<CommandVO> commandList) {
		this.commandList = commandList;
	}
	
	
	
}
