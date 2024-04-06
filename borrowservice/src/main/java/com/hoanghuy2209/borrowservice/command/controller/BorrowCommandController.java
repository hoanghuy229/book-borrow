package com.hoanghuy2209.borrowservice.command.controller;

import com.hoanghuy2209.borrowservice.command.command.CreateBorrowCommand;
import com.hoanghuy2209.borrowservice.command.command.DeleteBorrowCommand;
import com.hoanghuy2209.borrowservice.command.command.UpdateBookReturnCommand;
import com.hoanghuy2209.borrowservice.command.model.BorrowRequestModel;
import com.hoanghuy2209.borrowservice.command.service.IBorrowService;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrowing")
public class BorrowCommandController {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private IBorrowService borrowService;

    @PostMapping
    public String addBookBorrowing(@RequestBody BorrowRequestModel model) {
        try {
            CreateBorrowCommand command =
                    new CreateBorrowCommand( UUID.randomUUID().toString(),model.getBookId(), model.getEmployeeId(), new Date());
            commandGateway.sendAndWait(command);
            return "Book borrowing added";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();

        }
    }
    @PutMapping
    public String updateBookReturn(@RequestBody BorrowRequestModel model) {
        UpdateBookReturnCommand command = new UpdateBookReturnCommand(borrowService.findIdBorrowing(model.getEmployeeId(), model.getBookId()), model.getBookId(),model.getEmployeeId(),new Date());
        commandGateway.sendAndWait(command);
        return "Book returned";
    }
    @DeleteMapping("/{id}")
    public String deleteBookBorrowing(@PathVariable("id") String id){
        DeleteBorrowCommand command = new DeleteBorrowCommand(id);
        commandGateway.sendAndWait(command);
        return "Delete borrows complete";
    }
}
