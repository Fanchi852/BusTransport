package com.example.BusTransport.exception;

public class PassengerNotFoundException extends RuntimeException{

    public PassengerNotFoundException(Integer id){super("Passenger not found: " + id);}

}
