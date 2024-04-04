package com.hoanghuy2209.bookservice.command.controller;

import com.hoanghuy2209.bookservice.command.command.CreateBookCommand;
import com.hoanghuy2209.bookservice.command.command.DeleteBookCommand;
import com.hoanghuy2209.bookservice.command.command.UpdateBookCommand;
import com.hoanghuy2209.bookservice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/books")
public class BookCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping()
    public String addBook(@RequestBody BookRequestModel model){
        CreateBookCommand command = new CreateBookCommand(UUID.randomUUID().toString(),model.getName(),model.getAuthor(),true);
        commandGateway.sendAndWait(command);
        return "add success";
    }

    @PutMapping("/{id}")
    public String updateBook(@PathVariable("id") String id,@RequestBody BookRequestModel model){
        UpdateBookCommand command = new UpdateBookCommand(id,model.getName(),model.getAuthor(),model.getIsReady());
        commandGateway.sendAndWait(command);
        return "update success";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") String id){
        DeleteBookCommand command = new DeleteBookCommand(id);
        commandGateway.sendAndWait(command);
        return "delete success";
    }
}
