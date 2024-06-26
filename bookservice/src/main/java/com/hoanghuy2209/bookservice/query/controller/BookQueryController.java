package com.hoanghuy2209.bookservice.query.controller;

import com.hoanghuy2209.bookservice.query.model.BookResponseModel;
import com.hoanghuy2209.bookservice.query.queries.GetAllBookQuery;
import com.hoanghuy2209.bookservice.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/{id}")
    public BookResponseModel getBookById(@PathVariable("id") String id){
        GetBookQuery getBookQuery = new GetBookQuery();
        getBookQuery.setBookId(id);

        BookResponseModel bookResponseModel = queryGateway.query(getBookQuery,
                                                                ResponseTypes.instanceOf(BookResponseModel.class))
                                                                .join();
        return bookResponseModel;
    }

    @GetMapping()
    public List<BookResponseModel> getAllBook(){
        GetAllBookQuery getAllBookQuery = new GetAllBookQuery();

        List<BookResponseModel> bookResponseModels = queryGateway.query(getAllBookQuery,
                        ResponseTypes.multipleInstancesOf(BookResponseModel.class))
                .join();
        return bookResponseModels;
    }
}
