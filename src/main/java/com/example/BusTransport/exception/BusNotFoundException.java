package com.example.BusTransport.exception;

public class BusNotFoundException extends RuntimeException{

    public BusNotFoundException(Integer id){super("Bus not found: " + id);}
    public BusNotFoundException(){super("Bus not found");}

}
