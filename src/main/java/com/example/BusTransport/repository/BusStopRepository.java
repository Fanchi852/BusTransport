package com.example.BusTransport.repository;

import com.example.BusTransport.domain.BusStop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BusStopRepository extends CrudRepository<BusStop, Integer> {

    ArrayList<BusStop> findAll();
    Optional<BusStop> findById(Integer id);
    Set<BusStop> findByName(String name);
    BusStop save(BusStop busstop);
    void deleteById(Integer id);
    void deleteAll();
    long count();}
