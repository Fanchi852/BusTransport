package com.example.BusTransport.service;

import com.example.BusTransport.domain.PassengersBusesRelation;
import com.example.BusTransport.repository.PassengersBusesRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PassengersBusesRelationServiceImpl implements PassengersBusesRelationService{

    @Autowired
    private PassengersBusesRelationRepository passengersbusesrelationrepository;

    @Override
    public ArrayList<PassengersBusesRelation> findAll() {
        return passengersbusesrelationrepository.findAll();
    }

    @Override
    public Optional<PassengersBusesRelation> findById(Integer id) {
        return passengersbusesrelationrepository.findById(id);
    }

    @Override
    public PassengersBusesRelation save(PassengersBusesRelation passengersbusesrelation) {
        return passengersbusesrelationrepository.save(passengersbusesrelation);
    }

    @Override
    public PassengersBusesRelation modifyPassengersBusesRelation(PassengersBusesRelation passengersbusesrelation) {
        return passengersbusesrelationrepository.findById(passengersbusesrelation.getId()).get();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return passengersbusesrelationrepository.count();
    }}
