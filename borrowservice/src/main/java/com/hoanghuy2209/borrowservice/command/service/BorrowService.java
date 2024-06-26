package com.hoanghuy2209.borrowservice.command.service;
import com.hoanghuy2209.borrowservice.command.data.BorrowRepository;
import com.hoanghuy2209.borrowservice.command.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
@EnableBinding(Source.class)
public class BorrowService implements IBorrowService{

    @Autowired
    private BorrowRepository repository;

    @Autowired
    private MessageChannel output;

    @Override
    public void sendMessage(Message message) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(message);
            output.send(MessageBuilder.withPayload(json).build());
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public String findIdBorrowing(String employeeId, String bookId) {

        return	repository.findByEmployeeIdAndBookIdAndReturnDateIsNull(employeeId,bookId).getId();
    }

}