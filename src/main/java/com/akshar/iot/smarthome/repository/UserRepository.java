package com.akshar.iot.smarthome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akshar.iot.smarthome.model.User;



@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAll();
 //User findByEmailId(String emailId);
  User findByPhoneNo(String phoneNo);
  User findByUserId(int userId);
  public List<User> findByUserType(String userType);
 

}