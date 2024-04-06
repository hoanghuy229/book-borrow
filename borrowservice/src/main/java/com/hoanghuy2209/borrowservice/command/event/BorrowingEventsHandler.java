package com.hoanghuy2209.borrowservice.command.event;

import com.hoanghuy2209.borrowservice.command.data.BorrowRepository;
import com.hoanghuy2209.borrowservice.command.data.Borrowing;
import com.hoanghuy2209.borrowservice.command.model.Message;
import com.hoanghuy2209.borrowservice.command.service.IBorrowService;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BorrowingEventsHandler {
    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private IBorrowService borrowService;

    @EventHandler
    public void on(BorrowCreatedEvent event) {
        Borrowing model = new Borrowing();

        BeanUtils.copyProperties(event, model);

        borrowRepository.save(model);
    }

    @EventHandler
    public void on(BorrowDeletedEvent event){
        borrowRepository.deleteById(event.getId());
    }

    @EventHandler
    public void on(BorrowSendMessageEvent event) {
        Message message = new Message(event.getEmployeeId(), event.getMessage());
        borrowService.sendMessage(message);
    }
    @EventHandler
    public void on(BorrowingUpdateBookReturnEvent event) {
        Borrowing model = borrowRepository.findByEmployeeIdAndBookIdAndReturnDateIsNull(event.getEmployee(), event.getBookId());
        model.setReturnDate(event.getReturnDate());
        borrowRepository.save(model);
    }

}
