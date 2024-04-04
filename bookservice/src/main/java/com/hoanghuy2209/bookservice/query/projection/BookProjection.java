package com.hoanghuy2209.bookservice.query.projection;

import com.hoanghuy2209.bookservice.command.data.Book;
import com.hoanghuy2209.bookservice.command.data.BookRepository;
import com.hoanghuy2209.bookservice.query.model.BookResponseModel;
import com.hoanghuy2209.bookservice.query.queries.GetAllBookQuery;
import com.hoanghuy2209.bookservice.query.queries.GetBookQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public BookResponseModel handle(GetBookQuery getBookQuery){
        BookResponseModel model = new BookResponseModel();
        Book book = bookRepository.findById(getBookQuery.getBookId()).orElseThrow(null);
        BeanUtils.copyProperties(book,model);
        return model;
    }
    @QueryHandler
    public List<BookResponseModel> handle(GetAllBookQuery getAllBookQuery){
        List<BookResponseModel> models = new ArrayList<>();
        List<Book> books= bookRepository.findAll();
        books.forEach(book -> {
            BookResponseModel bookResponseModel = new BookResponseModel();
            BeanUtils.copyProperties(book,bookResponseModel);
            models.add(bookResponseModel);
        });
        return models;
    }
}
