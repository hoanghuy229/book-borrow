package com.hoanghuy2209.bookservice.command.aggregate;

import com.hoanghuy2209.bookservice.command.command.CreateBookCommand;
import com.hoanghuy2209.bookservice.command.command.DeleteBookCommand;
import com.hoanghuy2209.bookservice.command.command.UpdateBookCommand;
import com.hoanghuy2209.bookservice.command.event.BookCreateEvent;
import com.hoanghuy2209.bookservice.command.event.BookDeleteEvent;
import com.hoanghuy2209.bookservice.command.event.BookUpdateEvent;
import com.hoanghuy2209.commonservice.command.RollBackStatusBookCommand;
import com.hoanghuy2209.commonservice.command.UpdateStatusBookCommand;
import com.hoanghuy2209.commonservice.event.BookRollBackStatusEvent;
import com.hoanghuy2209.commonservice.event.BookUpdateStatusEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
@NoArgsConstructor
public class BookAggregrate {

    @AggregateIdentifier
    private String bookId;

    private String name;

    private String author;

    private Boolean isReady;

    @CommandHandler
    public BookAggregrate(CreateBookCommand command){
        BookCreateEvent event = new BookCreateEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(UpdateBookCommand command){
        BookUpdateEvent event = new BookUpdateEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(DeleteBookCommand command){
        BookDeleteEvent event = new BookDeleteEvent();
        BeanUtils.copyProperties(command,event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(UpdateStatusBookCommand command) {
        BookUpdateStatusEvent event = new BookUpdateStatusEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }
    @CommandHandler
    public void handle(RollBackStatusBookCommand command) {
        BookRollBackStatusEvent event = new BookRollBackStatusEvent();
        BeanUtils.copyProperties(command, event);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(BookCreateEvent event){
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookUpdateEvent event){
        this.bookId = event.getBookId();
        this.name = event.getName();
        this.author = event.getAuthor();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookDeleteEvent event){
        this.bookId = event.getBookId();
    }

    @EventSourcingHandler
    public void on(BookUpdateStatusEvent event) {
        this.bookId = event.getBookId();
        this.isReady = event.getIsReady();
    }

    @EventSourcingHandler
    public void on(BookRollBackStatusEvent event) {
        this.bookId = event.getBookId();
        this.isReady = event.getIsReady();
    }
}
