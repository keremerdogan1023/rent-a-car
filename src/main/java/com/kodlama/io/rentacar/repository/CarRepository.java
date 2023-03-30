package com.kodlama.io.rentacar.repository;

import com.kodlama.io.rentacar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Integer> {
}
