package com.example.BusTransport.exception;

public class LineNotFopundException extends RuntimeException{

    public LineNotFopundException(Integer id){super("Line not found: " + id);}

}
