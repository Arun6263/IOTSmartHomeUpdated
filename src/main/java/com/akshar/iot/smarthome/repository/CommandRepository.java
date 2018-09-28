package com.akshar.iot.smarthome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akshar.iot.smarthome.model.Command;
import com.akshar.iot.smarthome.model.Device;

@Repository("commandRepository")
public interface CommandRepository extends JpaRepository<Command, Long> {
	List<Command> findAll();
	List<Command> findByDevice(Device device);
}
