package com.akshar.iot.smarthome.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.akshar.iot.smarthome.form.DeviceForm;
import com.akshar.iot.smarthome.form.CommandForm;
import com.akshar.iot.smarthome.model.Device;
import com.akshar.iot.smarthome.model.Command;
import com.akshar.iot.smarthome.model.User;
import com.akshar.iot.smarthome.repository.DeviceRepository;
import com.akshar.iot.smarthome.repository.UserRepository;
import com.akshar.iot.smarthome.repository.CommandRepository;

import com.akshar.iot.smarthome.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeviceController {

	public final static Logger LOGGER = LoggerFactory.getLogger(DeviceController.class);
	
	@Autowired(required=true)
	 private DeviceRepository deviceRepository;
	
	@Autowired(required  = true)
	private CommandRepository commandRepository;
	
	
	@RequestMapping(value="/addDevice",method = RequestMethod.GET)
	public ModelAndView addDevice(){
		ModelAndView model = new ModelAndView();
		model.setViewName("user/addNewDevicePage");
		DeviceForm deviceForm = new DeviceForm();
		
		/*List<CommandForm> commandList = new ArrayList<CommandForm>(3);
		CommandForm commandForm = new CommandForm();
		commandForm.setCommandCode("");
		commandForm.setCommandDesc("");
		commandForm.setCommandValue("");
		commandList.add(commandForm);
		
		commandForm = new CommandForm();
		commandForm.setCommandCode("");
		commandForm.setCommandDesc("");
		commandForm.setCommandValue("");
		commandList.add(commandForm);
		
		commandForm = new CommandForm();
		commandForm.setCommandCode("");
		commandForm.setCommandDesc("");
		commandForm.setCommandValue("");
		commandList.add(commandForm);
		deviceForm.setCommandList(commandList);*/
		
		model.getModelMap().put("deviceForm",deviceForm);
		return model;
	}
	
	@RequestMapping(value = { "/saveDevice" }, method = RequestMethod.POST)
	public ModelAndView deviceSave(@ModelAttribute("deviceForm")DeviceForm deviceForm,Model modl) {
		
		ModelAndView model = null;
		//ModelAndView model=new ModelAndView("redirect:getAllDevices");
		
		Device device = new Device();
		Command command = null;
		Set commands = new HashSet<Command>();
		try {
			
			LOGGER.info("deviceSave : - Device Code "+ deviceForm.getDeviceCode());
			LOGGER.info("deviceSave : - Device Name "+ deviceForm.getDeviceName());
			LOGGER.info("deviceSave : - Product Code "+ deviceForm.getProductCode());
			
			/*List<CommandForm> commandList = deviceForm.getCommandList();
			LOGGER.info("deviceSave : - Command Size "+ commandList.size());
			for(CommandForm cForm : commandList){
				LOGGER.info("Command Info : Code : "+cForm.getCommandCode()+" Desc : "+cForm.getCommandDesc()+ "  Value : "+cForm.getCommandValue());
			}
			*/
			device.setDeviceCode(deviceForm.getDeviceCode());
			device.setDeviceName(deviceForm.getDeviceName());
			device.setProductCode(deviceForm.getProductCode());
			
			CommandForm commandForm = deviceForm.getCommandForm();
			LOGGER.info("deviceSave : - Command Code "+ commandForm.getCommandCode());
			LOGGER.info("deviceSave : - Command Desc "+ commandForm.getCommandDesc());
			LOGGER.info("deviceSave : - Command Value "+ commandForm.getCommandValue());
			
			command = new Command();
			command.setCommandCode(commandForm.getCommandCode());
			command.setCommandDesc(commandForm.getCommandDesc());
			command.setCommandValue(commandForm.getCommandValue());
			command.setDevice(device);		
			commands.add(command);
			
			
			/*	
				command = new Command();
				command.setCommandCode("OFF");
				command.setCommandDesc("OFF");
				command.setCommandValue("off");
				command.setDevice(device);
				
				commands.add(command);*/
				
				device.setCommands(commands);
				
		
				deviceRepository.save(device);
				
				String deviceCode = device.getDeviceCode();
				LOGGER.info("deviceSave :- ------successfully saved");
				
				model=new ModelAndView("redirect:getDeviceCommands/"+deviceCode);
		
		}
		catch (Exception e) {
			
			LOGGER.error("deviceSave :- Exception "+e.getMessage());
			 modl.addAttribute("error", "Problem in Login , Please try again "+e.getMessage());
			 model.setViewName("user/login");
		
		}
		return model;

	}
	@RequestMapping(value = {"/getDeviceCommands/{deviceCode}"}, method = RequestMethod.GET)
	public ModelAndView  getDeviceCommands(@PathVariable String deviceCode,Model modl){
		ModelAndView model = new ModelAndView();
		
		model.setViewName("user/deviceCommands");
		
		LOGGER.info("getDeviceCommands -- deviceCode : "+deviceCode);
		
		Device  device = deviceRepository.findByDeviceCode(deviceCode);
		
		LOGGER.info("getDeviceCommands -- deviceId : "+device.getDeviceId());
		LOGGER.info("getDeviceCommands -- deviceCode : "+device.getDeviceCode());
		LOGGER.info("getDeviceCommands -- deviceName : "+device.getDeviceName());
		LOGGER.info("getDeviceCommands -- Product Code : "+device.getProductCode());
		LOGGER.info("getDeviceCommands -- Command Count : "+device.getCommands().size());
		
		Set<Command> deviceCcmmands = device.getCommands();
		
		List<CommandForm> commandFormList = new ArrayList<CommandForm>();
		CommandForm commandForm = null;
		
		//List<Command> commandList = commandRepository.findByDevice(device);
		List<Command> commandList = new ArrayList<Command>();
		for(Command command : device.getCommands()){
			commandForm = new CommandForm();
			commandForm.setCommandCode(command.getCommandCode());
			commandForm.setCommandDesc(command.getCommandDesc());
			commandForm.setCommandValue(command.getCommandValue());
			commandFormList.add(commandForm);
		//	commandList.add(command);
			LOGGER.info(" getDeviceCommands :- Code : "+command.getCommandCode()+"  Desc : "+command.getCommandDesc()+ "   Value : "+command.getCommandValue());
		}
		
		DeviceForm deviceForm = new DeviceForm();
		deviceForm.setDeviceId(device.getDeviceId());
		deviceForm.setDeviceCode(device.getDeviceCode());
		deviceForm.setCommandList(commandFormList);

		modl.addAttribute("deviceForm", deviceForm);
		
		return model;
	}
	
	@RequestMapping(value = { "/saveDeviceCommand" }, method = RequestMethod.POST)
	public ModelAndView saveDeviceComands(@ModelAttribute("deviceForm")DeviceForm deviceForm,Model modl) {
	
		ModelAndView model = null;
		
		Device device = new Device();
		Command command = null;
		Set commands = new HashSet<Command>();
		try {
			LOGGER.info("saveDeviceComands : - Device Id "+ deviceForm.getDeviceId());
			LOGGER.info("saveDeviceComands : - Device Code "+ deviceForm.getDeviceCode());
		
				
			CommandForm commandForm = deviceForm.getCommandForm();
			LOGGER.info("saveDeviceComands : - Command Code "+ commandForm.getCommandCode());
			LOGGER.info("saveDeviceComands : - Command Desc "+ commandForm.getCommandDesc());
			LOGGER.info("saveDeviceComands : - Command Value "+ commandForm.getCommandValue());
			
			device.setDeviceId(deviceForm.getDeviceId());
			device.setDeviceCode(deviceForm.getDeviceCode());
			
			command = new Command();
			command.setCommandCode(commandForm.getCommandCode());
			command.setCommandDesc(commandForm.getCommandDesc());
			command.setCommandValue(commandForm.getCommandValue());
			command.setDevice(device);		
			
				
		
			commandRepository.save(command);
				
				int commandId = command.getCommandId();
				LOGGER.info("saveDeviceComands :- ------successfully saved ----commandId : "+commandId);
				
				model=new ModelAndView("redirect:getDeviceCommands/"+deviceForm.getDeviceCode());
		
		}
		catch (Exception e) {
			
			LOGGER.error("deviceSave :- Exception "+e.getMessage());
			 modl.addAttribute("error", "Problem in Login , Please try again "+e.getMessage());
			 model.setViewName("user/login");
		
		}
		return model;
	}
	@RequestMapping(value = { "/getAllDevices" }, method = RequestMethod.GET)
	public ModelAndView getAllDevices(Model modl) {
		ModelAndView model = new ModelAndView();
		 model.setViewName("user/devicesList");
		 
		 List<Device> deviceList = deviceRepository.findAll();
			for(Device device : deviceList){
				LOGGER.info(" getAllDevices :- Code : "+device.getDeviceCode()+"  Name : "+device.getDeviceName());
			}
	
			modl.addAttribute("deviceList", deviceList);
			 
			 LOGGER.info("getAllDevices :- You are in the LIST ALL Devices");
	        return model;
		

	}


	    
	@RequestMapping(value = { "/showDeviceDetails/{deviceCode}" }, method = RequestMethod.GET)
	public ModelAndView showDeviceDetails(@PathVariable String deviceCode,Model model){
		
	
	//public ModelAndView showDeviceDetails(Model model,@RequestParam("deviceCode")String deviceCode){
		ModelAndView mv = new ModelAndView();		//new edit
		
		LOGGER.info("showDeviceDetails -- deviceCode : "+deviceCode);
		
		mv.setViewName("user/devicesList");
		List<Device> deviceList = deviceRepository.findAll();
		for(Device device : deviceList){
			LOGGER.info(" getAllDevices :- Code : "+device.getDeviceCode()+"  Name : "+device.getDeviceName());
		}

		model.addAttribute("deviceList", deviceList);
		
		return mv;
	}
	
}
