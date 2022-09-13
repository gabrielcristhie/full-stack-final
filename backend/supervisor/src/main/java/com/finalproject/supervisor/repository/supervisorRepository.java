package com.finalproject.supervisor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.supervisor.entities.SupervisorEntity;

@Repository
public interface supervisorRepository extends JpaRepository<SupervisorEntity, Long>{
}
