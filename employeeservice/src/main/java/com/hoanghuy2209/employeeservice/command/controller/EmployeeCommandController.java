package com.hoanghuy2209.employeeservice.command.controller;

import com.hoanghuy2209.employeeservice.command.command.CreateEmployeeCommand;
import com.hoanghuy2209.employeeservice.command.command.DeleteEmployeeCommand;
import com.hoanghuy2209.employeeservice.command.command.UpdateEmployeeCommand;
import com.hoanghuy2209.employeeservice.command.model.EmployeeRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping()
    public String addEmployee(@RequestBody EmployeeRequestModel model){
        CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString(),model.getFirstName(),model.getLastName(),model.getKin(),false);
        commandGateway.sendAndWait(command);
        return "add success";
    }
    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable("id") String id,@RequestBody EmployeeRequestModel model){
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(id,model.getFirstName(),model.getLastName(),model.getKin(),model.getIsDisciplined());
        commandGateway.sendAndWait(command);
        return "update success";
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") String id){
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(id);
        commandGateway.sendAndWait(command);
        return "delete success";
    }
}
