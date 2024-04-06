package com.hoanghuy2209.employeeservice.command.event;

import lombok.Data;

@Data
public class CreateEmployeeEvent {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
