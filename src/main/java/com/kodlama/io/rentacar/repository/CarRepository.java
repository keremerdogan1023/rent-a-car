package com.kodlama.io.rentacar.repository;

import com.kodlama.io.rentacar.entities.Car;
import com.kodlama.io.rentacar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByStateIsNot(State state);
}
