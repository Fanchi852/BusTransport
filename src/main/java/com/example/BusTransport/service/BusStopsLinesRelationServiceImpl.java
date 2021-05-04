package com.example.BusTransport.service;

import com.example.BusTransport.domain.BusStopsLinesRelation;
import com.example.BusTransport.repository.BusStopsLinesRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BusStopsLinesRelationServiceImpl implements BusStopsLinesRelationService{

    @Autowired
    private BusStopsLinesRelationRepository busstopslinesrelationrepository;

    @Override
    public ArrayList<BusStopsLinesRelation> findAll() {
        return busstopslinesrelationrepository.findAll();
    }

    @Override
    public Optional<BusStopsLinesRelation> findById(Integer id) {
        return busstopslinesrelationrepository.findById(id);
    }

    @Override
    public BusStopsLinesRelation save(BusStopsLinesRelation busstopslinesrelation) {
        return busstopslinesrelationrepository.save(busstopslinesrelation);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return busstopslinesrelationrepository.count();
    }}
