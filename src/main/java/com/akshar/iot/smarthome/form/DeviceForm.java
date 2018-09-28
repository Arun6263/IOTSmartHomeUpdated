package com.akshar.iot.smarthome.form;

import java.util.List;
import com.akshar.iot.smarthome.form.CommandForm;

public class DeviceForm {

	int deviceId;
	String deviceCode;
	String deviceName;
	String productCode;
	String aliasName;
	char activeFlag;
	
	
	
	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	List<CommandForm> commandList;
	
	CommandForm commandForm;
	
	public CommandForm getCommandForm() {
		return commandForm;
	}

	public void setCommandForm(CommandForm commandForm) {
		this.commandForm = commandForm;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public char getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(char activeFlag) {
		this.activeFlag = activeFlag;
	}

	public List<CommandForm> getCommandList() {
		return commandList;
	}

	public void setCommandList(List<CommandForm> commandList) {
		this.commandList = commandList;
	}
	
	
	

}


