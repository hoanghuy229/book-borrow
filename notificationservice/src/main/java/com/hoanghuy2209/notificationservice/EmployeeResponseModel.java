package com.hoanghuy2209.notificationservice;

import lombok.Data;

@Data
public class EmployeeResponseModel {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
