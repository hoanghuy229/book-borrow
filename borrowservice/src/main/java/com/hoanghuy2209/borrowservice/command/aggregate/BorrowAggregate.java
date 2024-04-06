package com.hoanghuy2209.borrowservice.command.aggregate;

import com.hoanghuy2209.borrowservice.command.command.CreateBorrowCommand;
import com.hoanghuy2209.borrowservice.command.command.DeleteBorrowCommand;
import com.hoanghuy2209.borrowservice.command.command.SendMessageCommand;
import com.hoanghuy2209.borrowservice.command.command.UpdateBookReturnCommand;
import com.hoanghuy2209.borrowservice.command.event.BorrowCreatedEvent;
import com.hoanghuy2209.borrowservice.command.event.BorrowDeletedEvent;
import com.hoanghuy2209.borrowservice.command.event.BorrowSendMessageEvent;
import com.hoanghuy2209.borrowservice.command.event.BorrowingUpdateBookReturnEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Aggregate
@NoArgsConstructor
public class BorrowAggregate {
    @AggregateIdentifier
    private String id;

    private String bookId;
    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
    private String message;
    @CommandHandler
    public BorrowAggregate(CreateBorrowCommand command) {
        BorrowCreatedEvent event = new BorrowCreatedEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(DeleteBorrowCommand command){
        BorrowDeletedEvent event = new BorrowDeletedEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(SendMessageCommand command) {
        BorrowSendMessageEvent event = new BorrowSendMessageEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(UpdateBookReturnCommand command) {
        BorrowingUpdateBookReturnEvent event = new BorrowingUpdateBookReturnEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @EventSourcingHandler
    public void on(BorrowCreatedEvent event) {
        this.bookId = event.getBookId();
        this.borrowingDate = event.getBorrowingDate();
        this.employeeId = event.getEmployeeId();
        this.id = event.getId();

    }
    @EventSourcingHandler
    public void on(BorrowDeletedEvent event){
        this.id = event.getId();
    }

    @EventSourcingHandler
    public void on(BorrowSendMessageEvent event) {
        this.id = event.getId();
        this.message = event.getMessage();
        this.employeeId = event.getEmployeeId();
    }
    @EventSourcingHandler
    public void on(BorrowingUpdateBookReturnEvent event) {

        this.returnDate = event.getReturnDate();
        this.bookId = event.getBookId();
        this.employeeId = event.getEmployee();
    }
}
