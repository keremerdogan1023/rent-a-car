package com.kodlama.io.rentacar.repository;

import com.kodlama.io.rentacar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD operations
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    boolean existsByNameIgnoreCase(String name);
}
