package com.example.BusTransport.service;

import com.example.BusTransport.domain.BusStop;
import com.example.BusTransport.repository.BusStopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class BusStopServiceImpl implements BusStopService{

    @Autowired
    private BusStopRepository busstoprepository;

    @Override
    public ArrayList<BusStop> findAll() {
        return busstoprepository.findAll();
    }

    @Override
    public Optional<BusStop> findById(Integer id) {
        return busstoprepository.findById(id);
    }

    @Override
    public Set<BusStop> findByName(String name) {
        return busstoprepository.findByName(name);
    }

    @Override
    public BusStop save(BusStop busstop) {
        return busstoprepository.save(busstop);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return busstoprepository.count();
    }}
