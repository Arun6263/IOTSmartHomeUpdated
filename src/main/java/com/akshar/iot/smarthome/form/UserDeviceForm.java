package com.akshar.iot.smarthome.form;
import java.util.List;

import com.akshar.iot.smarthome.form.UserForm;
import com.akshar.iot.smarthome.form.DeviceForm;

public class UserDeviceForm {

	private int userDeviceId;
	
	private int userId;
	private String firstName;
	private String lastName;
	
	private List<UserForm> userList;
	
	private int controlBoxId;
	private String controlBoxCode;
	private String controlBoxName;
	
	private List<DeviceForm> deviceList;
	private int deviceId;
	private String deviceCode;
	private String deviceName;
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
	public List<UserForm> getUserList() {
		return userList;
	}
	public void setUserList(List<UserForm> userList) {
		this.userList = userList;
	}
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
	public List<DeviceForm> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<DeviceForm> deviceList) {
		this.deviceList = deviceList;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public int getUserDeviceId() {
		return userDeviceId;
	}
	public void setUserDeviceId(int userDeviceId) {
		this.userDeviceId = userDeviceId;
	}
	
	
	
}
