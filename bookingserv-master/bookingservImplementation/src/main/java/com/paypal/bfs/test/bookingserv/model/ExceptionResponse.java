package com.paypal.bfs.test.bookingserv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExceptionResponse {


    private String message;
    private String description;

}
