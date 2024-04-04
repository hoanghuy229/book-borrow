package com.hoanghuy2209.bookservice.command.model;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class BookRequestModel {
    @Id
    private String bookId;

    private String name;

    private String author;

    private Boolean isReady;
}
