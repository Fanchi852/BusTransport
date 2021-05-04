package com.example.BusTransport.exception;

public class PassengerNotFopundException extends RuntimeException{

    public PassengerNotFopundException(Integer id){super("Passenger not found: " + id);}

}
