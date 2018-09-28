package com.akshar.iot.smarthome.service.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


import com.akshar.iot.smarthome.service.UserService;
import com.akshar.iot.smarthome.webservice.rest.SmartHomeController;
import com.akshar.iot.smarthome.model.User;

import com.akshar.iot.smarthome.repository.UserRepository;
import com.akshar.iot.smarthome.service.UserService;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
 
	
public final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

@Autowired(required=true)
 private UserRepository userRepository;
 
 
/*public User findByEmailId(String email) {
	 return userRepository.findByEmailId(email);
}

*/
@PersistenceContext
EntityManager entityManager ;
public User findByPhoneNo(String phoneNo) {
	 return userRepository.findByPhoneNo(phoneNo);
}

public User findByUserId(int userId){
	 return userRepository.findByUserId(userId);
}

public List<User> findByUserType(String userType){
	return userRepository.findByUserType(userType);
}
public void saveUser(User user) {
	
	try{
		
		userRepository.save(user);
	
	}catch(Exception e){
		e.getMessage();
	}
	/*  user.setActive(1);
	  Role userRole = roleRespository.findByRole("USER");
	  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	  userRepository.save(user);*/
	  
	  
}

public List<User> getAllUsers() {
	
	List<User> userList = null;
	
	userList = userRepository.findAll();
	
	System.out.println("User Service Impl : "+ userList.size());
	return userList;
	
}

@Override
public int changePassword(Map<String, Object> userData) {
	String newpwd= (String)userData.get("newpassword");
	String phoneno= (String)userData.get("phoneno");
	String oldpassword= (String)userData.get("oldpassword");
	
	try{
		//int count=userRepository.changePassword(phoneno, oldpassword, newpwd);
		//return count;
		
		
	int count=	entityManager.createNamedQuery("updateUserPwd", User.class)
		.setParameter(1, newpwd)
		.setParameter(2, phoneno)
		.executeUpdate();
	
		return count;
		
	}
	
	 catch (Exception e)
    {
		 System.out.println("exception   "+e);
        return 0;
    }
	

}


}