package com.example.BusTransport.exception;

public class BusStopsLinesRelationNotFoundException extends RuntimeException{

    public BusStopsLinesRelationNotFoundException(Integer id){super("BusStopsLinesRelation not found: " + id);}

}
