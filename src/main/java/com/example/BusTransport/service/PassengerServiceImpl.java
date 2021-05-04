package com.example.BusTransport.service;

import com.example.BusTransport.domain.Passenger;
import com.example.BusTransport.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    private PassengerRepository passengerrepository;

    @Override
    public ArrayList<Passenger> findAll() {
        return passengerrepository.findAll();
    }

    @Override
    public Optional<Passenger> findById(Integer id) {
        return passengerrepository.findById(id);
    }

    @Override
    public Passenger save(Passenger passenger) {
        return passengerrepository.save(passenger);
    }

    @Override
    public Passenger modifyPassenger(Passenger passenger) {
        return passengerrepository.findById(passenger.getId()).get();
    }

    @Override
    public void deleteById(Integer id) {}

    @Override
    public void deleteAll() {}

    @Override
    public long count() {
        return passengerrepository.count();
    }}
