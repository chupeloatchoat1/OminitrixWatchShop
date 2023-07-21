package com.example.ominitrixw.repository;

import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
}
