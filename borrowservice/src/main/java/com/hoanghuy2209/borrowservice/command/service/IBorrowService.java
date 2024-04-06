package com.hoanghuy2209.borrowservice.command.service;

import com.hoanghuy2209.borrowservice.command.model.Message;

public interface IBorrowService {
    void sendMessage(Message message);
    String findIdBorrowing(String employeeId,String bookId);
}
