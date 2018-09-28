package com.akshar.iot.smarthome.webservice.vo;

import java.io.Serializable;

public class CommandVO implements Serializable{

   private String commandCode;
   private String commandValue;
public String getCommandCode() {
	return commandCode;
}
public void setCommandCode(String commandCode) {
	this.commandCode = commandCode;
}
public String getCommandValue() {
	return commandValue;
}
public void setCommandValue(String commandValue) {
	this.commandValue = commandValue;
}
   
   
   
}
