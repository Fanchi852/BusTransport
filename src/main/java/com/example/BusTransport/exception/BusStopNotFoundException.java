package com.example.BusTransport.exception;

public class BusStopNotFoundException extends RuntimeException{

    public BusStopNotFoundException(Integer id){super("BusStop not found: " + id);}

}
