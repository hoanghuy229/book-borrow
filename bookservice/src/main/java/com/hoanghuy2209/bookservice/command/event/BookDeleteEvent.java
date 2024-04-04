package com.hoanghuy2209.bookservice.command.event;

import lombok.Data;

@Data
public class BookDeleteEvent {
    private String bookId;
}
