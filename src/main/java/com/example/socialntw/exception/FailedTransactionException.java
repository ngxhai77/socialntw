package com.example.socialntw.exception;

import com.example.socialntw.parent.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class FailedTransactionException extends BaseException {
    public FailedTransactionException(String message) {
        super(message);
    }
}
