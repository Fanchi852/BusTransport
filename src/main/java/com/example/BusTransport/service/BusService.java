package com.example.BusTransport.service;

import com.example.BusTransport.domain.Bus;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

public interface BusService {

    ArrayList<Bus> findAll();
    Optional<Bus> findById (Integer id);
    Set<Bus> findByCode(String code);
    Set<Bus> findByConsumption (Float consumption);
    Set<Bus> findByCapacity (Integer capacity);
    Bus save(Bus bus);
    void deleteById(Integer id);
    void deleteAll();
    long count();

}
