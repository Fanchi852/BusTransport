package com.example.BusTransport.service;

import com.example.BusTransport.domain.Bus;
import com.example.BusTransport.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
public class BusServiceImpl implements BusService{

    @Autowired
    private BusRepository busrepository;

    public ArrayList<Bus> saveAll(ArrayList<Bus> buses){
        ArrayList<Bus> res = new ArrayList<>();
        busrepository.saveAll(buses).forEach(res::add);
        return res;
    }

    @Override
    public ArrayList<Bus> findAll() {
        return busrepository.findAll();
    }

    @Override
    public Optional<Bus> findById(Integer id) {
        return busrepository.findById(id);
    }

    @Override
    public Set<Bus> findByCode(String code) {
        return busrepository.findByCode(code);
    }

    @Override
    public Set<Bus> findByConsumption(Float consumption) {
        return busrepository.findByConsumption(consumption);
    }

    @Override
    public Set<Bus> findByCapacity(Integer capacity) {
        return busrepository.findByCapacity(capacity);
    }

    @Override
    public Bus save(Bus bus) {
        return busrepository.save(bus);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return 0;
    }}
