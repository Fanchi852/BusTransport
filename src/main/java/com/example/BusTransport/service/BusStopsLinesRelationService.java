package com.example.BusTransport.service;

import com.example.BusTransport.domain.BusStopsLinesRelation;

import java.util.ArrayList;
import java.util.Optional;

public interface BusStopsLinesRelationService {

    ArrayList<BusStopsLinesRelation> findAll();
    Optional<BusStopsLinesRelation> findById(Integer id);
    BusStopsLinesRelation save(BusStopsLinesRelation busstopslinesrelation);
    void deleteById(Integer id);
    void deleteAll();
    long count();

}
