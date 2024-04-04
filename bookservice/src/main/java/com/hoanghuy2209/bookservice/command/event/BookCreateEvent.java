package com.hoanghuy2209.bookservice.command.event;

import lombok.Data;

@Data
public class BookCreateEvent {
    private String bookId;

    private String name;

    private String author;

    private Boolean isReady;
}
