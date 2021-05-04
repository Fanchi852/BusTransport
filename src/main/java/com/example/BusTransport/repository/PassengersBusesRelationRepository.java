package com.example.BusTransport.repository;

import com.example.BusTransport.domain.PassengersBusesRelation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface PassengersBusesRelationRepository extends CrudRepository<PassengersBusesRelation, Integer> {

    ArrayList<PassengersBusesRelation> findAll();
    Optional<PassengersBusesRelation> findById(Integer id);
    PassengersBusesRelation save(PassengersBusesRelation passengersbusesrelation);
    void deleteById(Integer id);
    void deleteAll();
    long count();}
