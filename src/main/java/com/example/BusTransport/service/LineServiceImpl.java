package com.example.BusTransport.service;

import com.example.BusTransport.domain.Line;
import com.example.BusTransport.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LineServiceImpl implements LineService{

    @Autowired
    private LineRepository linerepository;

    @Override
    public ArrayList<Line> findAll() {
        return linerepository.findAll();
    }

    @Override
    public Optional<Line> findById(Integer id) {
        return linerepository.findById(id);
    }

    @Override
    public Line findByName(String name) {
        return linerepository.findByName(name);
    }

    @Override
    public Line save(Line line) {
        return linerepository.save(line);
    }

    @Override
    public Line modifyLine(Line line) {
        return linerepository.findById(line.getId()).get();
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public long count() {
        return linerepository.count();
    }}
