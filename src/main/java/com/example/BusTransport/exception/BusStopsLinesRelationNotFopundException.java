package com.example.BusTransport.exception;

public class BusStopsLinesRelationNotFopundException extends RuntimeException{

    public BusStopsLinesRelationNotFopundException(Integer id){super("BusStopsLinesRelation not found: " + id);}

}
