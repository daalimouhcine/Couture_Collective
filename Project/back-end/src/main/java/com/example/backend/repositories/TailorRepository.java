package com.example.backend.repositories;

import com.example.backend.entities.TailorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TailorRepository extends CrudRepository<TailorEntity, Long> {
    @Query("SELECT t FROM TailorEntity t")
    List<TailorEntity> getAllTailors();
    TailorEntity findByEmail(String email);
}
