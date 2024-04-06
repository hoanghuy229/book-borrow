package com.hoanghuy2209.borrowservice.command.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowSendMessageEvent {
    private String id;

    private String employeeId;
    private String message;
}