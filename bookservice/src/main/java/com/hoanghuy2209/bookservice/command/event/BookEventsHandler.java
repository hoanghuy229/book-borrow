package com.hoanghuy2209.bookservice.command.event;

import com.hoanghuy2209.bookservice.command.data.Book;
import com.hoanghuy2209.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookEventsHandler {

    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreateEvent event){
        Book book = new Book();
        BeanUtils.copyProperties(event,book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdateEvent event){
        Book book = bookRepository.findById(event.getBookId()).orElseThrow(null);
        book.setAuthor(event.getAuthor());
        book.setName(event.getName());
        book.setIsReady(event.getIsReady());
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookDeleteEvent event){
        bookRepository.deleteById(event.getBookId());
    }
}
