package com.hoanghuy2209.borrowservice.command.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "borrows")
@Data
public class Borrowing {
    @Id
    private String id;

    private String bookId;

    private String employeeId;
    private Date borrowingDate;
    private Date returnDate;
}
