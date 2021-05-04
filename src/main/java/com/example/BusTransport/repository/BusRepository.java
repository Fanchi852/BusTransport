package com.example.BusTransport.repository;

import com.example.BusTransport.domain.Bus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BusRepository extends CrudRepository<Bus, Integer> {

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
