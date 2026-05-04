package com.example.Hotelbooking.Exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    private String error;
    private String path;

    public ErrorResponse(String error,int statusCode, String message,String path) {
        this.error = error;
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getError() {
        return error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
