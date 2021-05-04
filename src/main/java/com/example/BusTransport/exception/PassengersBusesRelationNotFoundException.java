package com.example.BusTransport.exception;

public class PassengersBusesRelationNotFoundException extends RuntimeException{

    public PassengersBusesRelationNotFoundException(Integer id){super("PassengersBusesRelation not found: " + id);}

}
