package com.example.backend.repositories;

import com.example.backend.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {
    List<ClientEntity> findAllByTailorId(Long id);
}
