package com.hoanghuy2209.commonservice.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetBookDetailQuery {
    private String bookId;
}