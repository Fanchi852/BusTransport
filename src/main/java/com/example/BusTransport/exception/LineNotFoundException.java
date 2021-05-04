package com.example.BusTransport.exception;

public class LineNotFoundException extends RuntimeException{

    public LineNotFoundException(Integer id){super("Line not found: " + id);}

}
