package com.example.socialntw.exception;

import com.example.socialntw.parent.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.METHOD_NOT_ALLOWED)
public class UploadFileException extends BaseException {
    public UploadFileException(String message) {
        super(message);
    }
}
