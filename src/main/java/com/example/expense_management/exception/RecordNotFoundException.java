package com.example.expense_management.exception;

import java.util.Date;

public class RecordNotFoundException extends ExceptionResponse{

    public RecordNotFoundException(Date timestamp, String message, String details) {
        super(timestamp, message, details);
    }

    public RecordNotFoundException(String s) {
        super();
    }
}