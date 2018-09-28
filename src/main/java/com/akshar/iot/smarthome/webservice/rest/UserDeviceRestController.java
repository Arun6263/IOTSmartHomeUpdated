package com.akshar.iot.smarthome.webservice.rest;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.CacheControl;


//import com.akshar.iot.smarthome.mqtt.publisher.MQTTPublisherBase;
import com.akshar.iot.smarthome.service.UserService;
import com.akshar.iot.smarthome.webservice.vo.UserSwitchDetailVO;
import com.akshar.iot.smarthome.webservice.vo.ControlBoxVO;
import com.akshar.iot.smarthome.webservice.vo.UserDeviceVO;
import com.akshar.iot.smarthome.webservice.vo.CommandVO;

import com.akshar.iot.smarthome.model.User;
import com.akshar.iot.smarthome.model.Device;
import com.akshar.iot.smarthome.model.ControlBox;
import com.akshar.iot.smarthome.model.UserDevice;
import com.akshar.iot.smarthome.model.Command;

import com.akshar.iot.smarthome.repository.DeviceRepository;
import com.akshar.iot.smarthome.repository.UserDeviceRepository;
import com.akshar.iot.smarthome.repository.ControlBoxRepository;
import com.akshar.iot.smarthome.repository.CommandRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "UserDeviceRestController  API", description = "<b>UserDeviceRestController  API</b> \n\n"
	+ "Use this API for Add, Find, Remove the User Home devices based on SmartHome APP calls")

@RestController
public class UserDeviceRestController {
	
	public final static Logger LOGGER = LoggerFactory.getLogger(UserDeviceRestController.class);
	
	//@Autowired
	//MQTTPublisherBase publisher;
	
	@Autowired
	private UserService userService;

	@Autowired
	private ControlBoxRepository controlBoxRepository;
	
	@Autowired
	private UserDeviceRepository userDeviceRepository;

	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private CommandRepository commandRepository;

	
	@ApiOperation(value = "Get UserDevice Details")
	@PostMapping(value = "/user/getUserDeviceDetails")
	//public ResponseEntity<Map<String, Object>> getUserDeviceDetails(@RequestBody Map<String, Object> userData) {
	public ResponseEntity<UserSwitchDetailVO> getUserDeviceDetails(@RequestBody Map<String, Object> userData) {
		
		String phoneNo = (String)userData.get("PhoneNo");
		
		LOGGER.info("getUserDetails :- phoneNo : "+phoneNo);
		LOGGER.info("getUserDetails :- UserType : "+userData.get("UserType"));
		
			
		UserSwitchDetailVO userSwitchDetailsVO = null;
		List<ControlBoxVO> controlBoxVOList = null;
		ControlBoxVO controlBoxVO = null;
		
		List<UserDeviceVO> userDevicesVOList = null;
		UserDeviceVO userDeviceVO = null;
		
		List<CommandVO> commandVOList = null;
		CommandVO commandVO = null;

				

		List<UserDevice> userDeviceList = null;
		Device device = null;
		Command command = null;
		List<Command> deviceCommands = null;
		
		
		User user = userService.findByPhoneNo(phoneNo);
		
		if(user!=null) {
			LOGGER.info(" getUserDeviceDetails() ::: "+phoneNo+" EmailId: "+ user.getEmailId() + " First Name : "+ user.getFirstName()+"Last Name : "+user.getLastName());
		}
				
		userSwitchDetailsVO = new UserSwitchDetailVO();
		
		userSwitchDetailsVO.setUserId(user.getUserId());
		userSwitchDetailsVO.setPhoneNo(user.getPhoneNo());
		userSwitchDetailsVO.setFirstName(user.getFirstName());
		userSwitchDetailsVO.setLastName(user.getLastName());
		userSwitchDetailsVO.setUserId(user.getUserId());
		
		controlBoxVOList = new ArrayList<ControlBoxVO>();
		
		List<ControlBox> userControlBoxes = controlBoxRepository.findByUserId(user.getUserId());
		
		if(user!=null) {
			LOGGER.info(" getUserDeviceDetails() ::: Control Box Size"+userControlBoxes.size());
		}
		for(ControlBox controlBox : userControlBoxes){
			
			controlBoxVO = new ControlBoxVO();
			
			controlBoxVO.setControlBoxId(controlBox.getControlboxId());
			controlBoxVO.setControlBoxCode(controlBox.getControlboxCode());
			controlBoxVO.setControlBoxName(controlBox.getControlboxName());
			
			userDevicesVOList = new ArrayList<UserDeviceVO>();
						
			userDeviceList = userDeviceRepository.findByControlBox(controlBox);
			
			for(UserDevice userDevice : userDeviceList)
			{
				
				device = deviceRepository.findByDeviceId(userDevice.getDeviceId());

				userDeviceVO = new UserDeviceVO();
				userDeviceVO.setUserDeviceId(userDevice.getUserDeviceId());
				userDeviceVO.setUserDeviceCode(userDevice.getAliasName());
				userDeviceVO.setDeviceStatus(userDevice.getDeviceStatus());

				
				deviceCommands = commandRepository.findByDevice(device);
				
				commandVOList = new ArrayList<CommandVO>();
				
				for(Command comm : deviceCommands){
				
					commandVO = new CommandVO();
					commandVO.setCommandCode(comm.getCommandCode());
					commandVO.setCommandValue(comm.getCommandValue());
					commandVOList.add(commandVO);
				}
				userDeviceVO.setCommandList(commandVOList);
				
				userDevicesVOList.add(userDeviceVO);	
			}
			
			controlBoxVO.setUserDevices(userDevicesVOList);
			
			controlBoxVOList.add(controlBoxVO);
		}
		
		userSwitchDetailsVO.setControlBoxList(controlBoxVOList);
		
		return ResponseEntity.status(HttpStatus.OK).body(userSwitchDetailsVO);
		    
    }

	@ApiOperation(value = "Change User Device Status")
	@PostMapping(value = "/user/changeUserDeviceStatus")
	//public ResponseEntity<Map<String, Object>> getUserDeviceDetails(@RequestBody Map<String, Object> userData) {
	public String changeUserDeviceStatus(@RequestBody Map<String, Object> userData) {
		
		String phoneNo = (String)userData.get("PhoneNo");
		String controlBoxCode = (String)userData.get("ControlBoxCode");
		String userDeviceCode = (String)userData.get("UserDeviceCode");
		String deviceCommand = (String)userData.get("DeviceCommand");
		String userDeviceId   =(String) userData.get("UserDeviceId");
		LOGGER.info("changeUserDeviceStatus :- phoneNo : "+phoneNo);
		LOGGER.info("changeUserDeviceStatus :- UserType : "+userData.get("UserType"));
		LOGGER.info("changeUserDeviceStatus :- controlBoxCode : "+controlBoxCode);
		LOGGER.info("changeUserDeviceStatus :- userDeviceCode : "+userDeviceCode);
		LOGGER.info("changeUserDeviceStatus :- deviceCommand : "+deviceCommand);
			
			
		String topicName = phoneNo+"/"+controlBoxCode+"/"+userDeviceCode;

		//publisher.publishMessage(topicName, deviceCommand);
		//return "Message sent to Broker";
		
		User user = userService.findByPhoneNo(phoneNo);
		
		ControlBox controlBox = controlBoxRepository.findByControlboxCode(controlBoxCode);
		
		UserDevice userDevice = userDeviceRepository.findByUserDeviceId(Integer.parseInt(userDeviceId));
		userDevice.setDeviceStatus(deviceCommand);
		
		
		userDeviceRepository.save(userDevice);
		
		
		return "Success";
    
    }

}
