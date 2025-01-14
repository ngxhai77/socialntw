package com.example.socialntw.parent;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private int statusCode;
    private String message;
    private Object data;

    public ApiResponse(int statusCode, Object data) {
        this.statusCode = statusCode;
        this.message = "Successfully";
        this.data = data;
    }

    public ApiResponse(int statusCode) {
        this.statusCode = statusCode;
        this.message = "Successfully";
        this.data = "No contents";
    }

}
