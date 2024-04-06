package com.hoanghuy2209.borrowservice.command.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String employeeId;
    private String message;
}