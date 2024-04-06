package com.hoanghuy2209.employeeservice.command.event;

import com.hoanghuy2209.employeeservice.command.data.Employee;
import com.hoanghuy2209.employeeservice.command.data.EmployeeRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEventHandler {
    @Autowired
    private EmployeeRepository employeeRepository;

    @EventHandler
    public void on(CreateEmployeeEvent event){
        Employee employee = new Employee();
        BeanUtils.copyProperties(event,employee);
        employeeRepository.save(employee);
    }
    @EventHandler
    public void on(UpdateEmployeeEvent event){
        Employee employee = employeeRepository.findById(event.getEmployeeId()).orElseThrow(null);
        employee.setKin(event.getKin());
        employee.setFirstName(event.getFirstName());
        employee.setLastName(event.getLastName());
        employee.setIsDisciplined(event.getIsDisciplined());
        employeeRepository.save(employee);
    }
    @EventHandler
    public void on(DeleteEmployeeEvent event){
       employeeRepository.deleteById(event.getEmployeeId());
    }
}
