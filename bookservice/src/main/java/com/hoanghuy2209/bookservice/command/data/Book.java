package com.hoanghuy2209.bookservice.command.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    private String bookId;

    private String name;

    private String author;

    private Boolean isReady;




}
