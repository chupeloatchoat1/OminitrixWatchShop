package com.example.ominitrixw.repository;

import com.example.ominitrixw.entities.WatchType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchTypeRepository extends JpaRepository<WatchType, String> {
}
