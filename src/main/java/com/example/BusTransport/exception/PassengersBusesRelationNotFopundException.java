package com.example.BusTransport.exception;

public class PassengersBusesRelationNotFopundException extends RuntimeException{

    public PassengersBusesRelationNotFopundException(Integer id){super("PassengersBusesRelation not found: " + id);}

}
