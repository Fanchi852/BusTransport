package com.example.BusTransport.exception;

public class BusStopNotFopundException extends RuntimeException{

    public BusStopNotFopundException(Integer id){super("BusStop not found: " + id);}

}
