package com.hoanghuy2209.borrowservice.command.event;

import lombok.Data;

import java.util.Date;

@Data
public class BorrowCreatedEvent {
    private String id;
    private String bookId;
    private String employeeId;
    private Date borrowingDate;
}
