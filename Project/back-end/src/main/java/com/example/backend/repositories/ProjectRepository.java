package com.example.backend.repositories;

import com.example.backend.entities.ProjectEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    ProjectEntity findAllByTitle(String projectTitle);

    // findAllByClientId
    @Query(value = "SELECT * FROM projects WHERE fk_client_id = ?1", nativeQuery = true)
    Iterable<ProjectEntity> findAllByClientId(Long clientId);

    // findAllByTailorId
    @Query(value = "SELECT * FROM projects WHERE fk_tailor_id = ?1", nativeQuery = true)
    Iterable<ProjectEntity> findAllByTailorId(Long tailorId);

    // findAllByTailorIdAndClientId
    @Query(value = "SELECT * FROM projects WHERE fk_tailor_id = ?1 AND fk_client_id = ?2", nativeQuery = true)
    Iterable<ProjectEntity> findAllByTailorIdAndClientId(Long tailorId, Long clientId);

    // findAllByTailorIdAndDone
    @Query(value = "SELECT * FROM projects WHERE fk_tailor_id = ?1 AND is_completed = ?2", nativeQuery = true)
    Iterable<ProjectEntity> findAllByTailorIdAndDone(Long tailorId, Boolean done);

    // findAllByTailorIdAndNotDone
    @Query(value = "SELECT * FROM projects WHERE fk_tailor_id = ?1 AND is_completed = ?2", nativeQuery = true)
    Iterable<ProjectEntity> findAllByTailorIdAndNotDone(Long tailorId, Boolean done);

}
