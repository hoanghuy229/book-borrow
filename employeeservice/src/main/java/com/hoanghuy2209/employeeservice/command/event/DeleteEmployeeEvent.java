package com.hoanghuy2209.employeeservice.command.event;

import lombok.Data;

@Data
public class DeleteEmployeeEvent {
    private String employeeId;
}
