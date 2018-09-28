package com.akshar.iot.smarthome.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.akshar.iot.smarthome.form.CommandForm;
import com.akshar.iot.smarthome.form.DeviceForm;
import com.akshar.iot.smarthome.form.UserDeviceForm;
import com.akshar.iot.smarthome.form.ControlBoxForm;
import com.akshar.iot.smarthome.model.Command;
import com.akshar.iot.smarthome.model.ControlBox;
import com.akshar.iot.smarthome.model.Device;
import com.akshar.iot.smarthome.model.User;
import com.akshar.iot.smarthome.model.ControlBox;
import com.akshar.iot.smarthome.model.UserDevice;
import com.akshar.iot.smarthome.repository.DeviceRepository;
import com.akshar.iot.smarthome.repository.UserDeviceRepository;
import com.akshar.iot.smarthome.repository.ControlBoxRepository;
import com.akshar.iot.smarthome.repository.UserRepository;
import com.akshar.iot.smarthome.form.UserForm;
import com.akshar.iot.smarthome.form.DeviceForm;

@Controller
public class UserDeviceController {
	public final static Logger LOGGER = LoggerFactory.getLogger(UserDeviceController.class);
	
	@Autowired(required=true)
	 private UserDeviceRepository userDeviceRepository;
	
	@Autowired(required=true)
	 private UserRepository userRepository;
	
	@Autowired(required=true)
	 private DeviceRepository deviceRepository;
	
	@Autowired(required=true)
	 private ControlBoxRepository controlBoxRepository;
	
	@RequestMapping(value="/mapDeviceToUser",method = RequestMethod.GET)
	public ModelAndView mapDeviceToUser(Model model){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("user/mapDeviceToUser");
		UserDeviceForm userDeviceForm = new UserDeviceForm();
		List<UserForm> userList = new ArrayList<UserForm>();
		
		List<User> listOfUser = userRepository.findByUserType("CUST");
		
		UserForm userForm = null;
		
		for(User user : listOfUser){
			userForm = new UserForm();
			userForm.setUserId(user.getUserId());
			userForm.setFirstName(user.getFirstName());
			userForm.setLastName(user.getLastName());
			userList.add(userForm);
		}
		/*userForm.setUserId(6);
		userForm.setFirstName("Hari");
		userForm.setLastName("Prasad");
		userList.add(userForm);
		
		userForm = new UserForm();
		userForm.setUserId(8);
		userForm.setFirstName("Honey");
		userForm.setLastName("Hyderabad");
		userList.add(userForm);
		*/
		
		List<Device> listOfDevices = deviceRepository.findAll();
		
		List<DeviceForm> deviceList = new ArrayList<DeviceForm>();
		
		DeviceForm deviceForm = null;
		
		for(Device device : listOfDevices){
			deviceForm = new DeviceForm();
			deviceForm.setDeviceId(device.getDeviceId());
			deviceForm.setDeviceCode(device.getDeviceCode());
			deviceForm.setDeviceName(device.getDeviceName());
			deviceForm.setProductCode(device.getProductCode());
			deviceList.add(deviceForm);
		}

		
		/*deviceForm = new DeviceForm();
		deviceForm.setDeviceId(1);
		deviceForm.setDeviceCode("FRIDGE");
		deviceForm.setDeviceName("Fridge");
		deviceForm.setProductCode("Samsung");
		deviceList.add(deviceForm);
		
		deviceForm = new DeviceForm();
		deviceForm.setDeviceId(1);
		deviceForm.setDeviceCode("TV");
		deviceForm.setDeviceName("Television");
		deviceForm.setProductCode("LG");
		deviceList.add(deviceForm);*/
		
	//	userDeviceForm.setUserList(userList);
		//userForm.se
		
	model.addAttribute("userDeviceForm",userDeviceForm);
		model.addAttribute("userList",userList);
		model.addAttribute("deviceList",deviceList);
	//	mv.getModelMap().put("userDeviceForm",userDeviceForm);
		//model.getModelMap().put("deviceForm",deviceForm);
		return mv;
	}
	
	
	
	@RequestMapping(value="/saveUserDevices",method = RequestMethod.POST)
	public ModelAndView saveUserDevices(@ModelAttribute("userDeviceForm")UserDeviceForm userDeviceForm,Model modl) {
		ModelAndView model = new ModelAndView();
		//model.setViewName("user/mapDeviceToUser");
		
		LOGGER.info("saveUserDevices: -      userDeviceForm- UserId : "+userDeviceForm.getUserId());
		LOGGER.info("saveUserDevices: -      userDeviceForm- ControlBoxCode : "+userDeviceForm.getControlBoxCode());
		LOGGER.info("saveUserDevices: -      userDeviceForm- ControlBoxName : "+userDeviceForm.getControlBoxCode());
		LOGGER.info("saveUserDevices: -      userDeviceForm- deviceId : "+userDeviceForm.getDeviceId());
		LOGGER.info("saveUserDevices: -      userDeviceForm- device Alias Name : "+userDeviceForm.getDeviceName());
		
		ControlBox controlBox = new ControlBox();
		
		controlBox.setControlboxCode(userDeviceForm.getControlBoxCode());
		controlBox.setControlboxName(userDeviceForm.getControlBoxCode());
		controlBox.setUserId(userDeviceForm.getUserId());
		
		UserDevice userDevice = new UserDevice();
		userDevice.setAliasName(userDeviceForm.getDeviceName());
		userDevice.setDeviceId(userDeviceForm.getDeviceId());
		userDevice.setDeviceStatus("Off");
		
		Set<UserDevice> userDevices = new HashSet<UserDevice>();
		userDevices.add(userDevice);
		
		controlBox.setUserDevices(userDevices);
		userDevice.setControlBox(controlBox);
		
		controlBoxRepository.save(controlBox);
		//model.getModelMap().put("deviceForm",deviceForm);
		
		model=new ModelAndView("redirect:getDeviceControllerList");
		return model;
	}
	
	@RequestMapping(value="/getControlsOfUser",method = RequestMethod.GET)
	public ModelAndView getControlsOfUser(){
		ModelAndView model = new ModelAndView();
		model.setViewName("user/mapDeviceToUser");
		
		
		//model.getModelMap().put("deviceForm",deviceForm);
		return model;
	}
	
	@RequestMapping(value="/getDeviceControllerList",method = RequestMethod.GET)
	public ModelAndView deviceControllerList(){
		ModelAndView model = new ModelAndView();
		model.setViewName("user/deviceControllerList");
		LOGGER.info("-----------deviceControllerList ----------  ");
		
		List<ControlBoxForm> controlBoxFormList =  new ArrayList<ControlBoxForm>();
		ControlBoxForm controlBoxForm = null;
		User user = null;
		List<ControlBox> controlBoxList = controlBoxRepository.findAll();
		
		for(ControlBox controlBox : controlBoxList){
			
			LOGGER.info("deviceControllerList :- ControlBox - Id :"+ controlBox.getControlboxId()+" Code : "+ controlBox.getControlboxCode()+" Name : "+controlBox.getControlboxName()+ " UserId : "+controlBox.getUserId());
		
			controlBoxForm =  new ControlBoxForm();
			controlBoxForm.setControlBoxId(controlBox.getControlboxId());
			controlBoxForm.setControlBoxCode(controlBox.getControlboxCode());
			controlBoxForm.setControlBoxName(controlBox.getControlboxName());
			controlBoxForm.setUserId(controlBox.getUserId());
			
			user = userRepository.findByUserId(controlBox.getUserId());
			
			controlBoxForm.setUserFirstName(user.getFirstName());
			controlBoxForm.setUserLastName(user.getLastName());
			
			controlBoxFormList.add(controlBoxForm);
			//	controlBoxFormList
		}
		
		
		
		
		//User user = new User();
		
		
		model.getModelMap().put("controlBoxFormList",controlBoxFormList);
		return model;
	}
	
	
	
	
	
	@RequestMapping(value = {"/addDeviceToUserControlBox/{controlBoxId}"}, method = RequestMethod.GET)
	public ModelAndView  addDeviceToUserControlBox(@PathVariable String controlBoxId,Model modl){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("user/addDeviceToController");
		LOGGER.info("addDeviceToUserControlBox -- controlBoxId : "+controlBoxId);
		
		ControlBox control = controlBoxRepository.findByControlboxId(Integer.parseInt(controlBoxId));
		Set<UserDevice> userDeviceSet = control.getUserDevices();
		
		User user = userRepository.findByUserId(control.getUserId());
		
		UserDeviceForm userDeviceForm = new UserDeviceForm();
		userDeviceForm.setControlBoxId(control.getControlboxId());
		userDeviceForm.setControlBoxCode(control.getControlboxCode());
		userDeviceForm.setControlBoxName(control.getControlboxName());
		userDeviceForm.setUserId(user.getUserId());
		userDeviceForm.setFirstName(user.getFirstName());
		userDeviceForm.setLastName(user.getLastName());
		
		
		
		List<DeviceForm> deviceList = new ArrayList();
		DeviceForm deviceForm = new DeviceForm();
		Device device = null;
		
		for(UserDevice userDevice : userDeviceSet){
			
			device = deviceRepository.findByDeviceId(userDevice.getDeviceId());
			
			deviceForm.setDeviceCode(device.getDeviceCode());
			deviceForm.setDeviceName(device.getDeviceName());
			deviceForm.setProductCode(device.getProductCode());
			deviceForm.setAliasName(userDevice.getAliasName());
			
			deviceList.add(deviceForm);
		}
		
		userDeviceForm.setDeviceList(deviceList);
		
		
		List<Device> deviceObjList = deviceRepository.findAll();
		
		List<DeviceForm> listOfDevices = new ArrayList<DeviceForm>();
		
		//DeviceForm deviceForm = null;
		
		for(Device deviceObj : deviceObjList){
			deviceForm = new DeviceForm();
			deviceForm.setDeviceId(deviceObj.getDeviceId());
			deviceForm.setDeviceCode(deviceObj.getDeviceCode());
			deviceForm.setDeviceName(deviceObj.getDeviceName());
			deviceForm.setProductCode(deviceObj.getProductCode());
			listOfDevices.add(deviceForm);
		}
		
		
		modl.addAttribute("listOfDevices",listOfDevices);
		modl.addAttribute("userDeviceForm",userDeviceForm);
	//	modl.addAttribute("userDeviceList",userDeviceList);
		
		return mv;
	}
	
	
	@RequestMapping(value="/saveDeviceMappingToControl",method = RequestMethod.POST)
	public ModelAndView saveDeviceMappingToControl(@ModelAttribute("userDeviceForm")UserDeviceForm userDeviceForm,Model modl) {
		ModelAndView model = new ModelAndView();
		//model.setViewName("user/mapDeviceToUser");
		
		LOGGER.info("saveUserDevices: -      userDeviceForm- controlBoxId : "+userDeviceForm.getControlBoxId());
		LOGGER.info("saveUserDevices: -      userDeviceForm- ControlBoxCode : "+userDeviceForm.getControlBoxCode());
		//LOGGER.info("saveUserDevices: -      userDeviceForm- ControlBoxName : "+userDeviceForm.getControlBoxCode());
		LOGGER.info("saveUserDevices: -      userDeviceForm- deviceId : "+userDeviceForm.getDeviceId());
		LOGGER.info("saveUserDevices: -      userDeviceForm- device Alias Name : "+userDeviceForm.getDeviceName());
		
		ControlBox controlBox = new ControlBox();
		controlBox.setControlboxId(userDeviceForm.getControlBoxId());
		controlBox.setControlboxCode(userDeviceForm.getControlBoxCode());
		
		//controlBox.setControlboxName(userDeviceForm.getControlBoxCode());
		//controlBox.setUserId(userDeviceForm.getUserId());
		
		UserDevice userDevice = new UserDevice();
		userDevice.setDeviceId(userDeviceForm.getDeviceId());
		userDevice.setAliasName(userDeviceForm.getDeviceName());
		userDevice.setDeviceStatus("Off");
		userDevice.setControlBox(controlBox);
		
		userDeviceRepository.save(userDevice);
		
//		controlBoxRepository.save(controlBox);
		//model.getModelMap().put("deviceForm",deviceForm);
		
		model=new ModelAndView("redirect:addDeviceToUserControlBox/"+userDeviceForm.getControlBoxId());
		return model;
	}
	
	
	
	
}
