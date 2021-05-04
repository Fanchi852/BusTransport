package com.example.BusTransport.service;

import com.example.BusTransport.domain.PassengersBusesRelation;

import java.util.ArrayList;
import java.util.Optional;

public interface PassengersBusesRelationService {

    ArrayList<PassengersBusesRelation> findAll();
    Optional<PassengersBusesRelation> findById(Integer id);
    PassengersBusesRelation save(PassengersBusesRelation passengersbusesrelation);
    PassengersBusesRelation modifyPassengersBusesRelation(PassengersBusesRelation passengersbusesrelation);
    void deleteById(Integer id);
    void deleteAll();
    long count();

}
