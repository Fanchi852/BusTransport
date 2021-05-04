package com.example.BusTransport.service;

import com.example.BusTransport.domain.Line;

import java.util.ArrayList;
import java.util.Optional;

public interface LineService {

    ArrayList<Line> findAll();
    Optional<Line> findById(Integer id);
    Line findByName(String name);
    Line save(Line line);
    Line modifyLine(Line line);
    void deleteById(Integer id);
    void deleteAll();
    long count();

}
