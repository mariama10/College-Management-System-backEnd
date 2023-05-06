package com.example.demo.utils;
import java.util.Calendar;
import java.util.Date;

public class ApiResponse<T> {

    private T data;
    private ResponseStatus status = ResponseStatus.SUCCESS;;
    private String message = "SUCCESSFUL";
    private Date timeStamp = Calendar.getInstance().getTime();


    public ApiResponse(T data){
        this.data = data;
    }

    public ApiResponse(T data, ResponseStatus status, String message){
        this.data = data;
        this.status = status;
        this.message = message;
    }
}
