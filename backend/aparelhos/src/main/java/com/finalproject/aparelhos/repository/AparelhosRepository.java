package com.finalproject.aparelhos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finalproject.aparelhos.entities.AparelhoEntity;

@Repository
public interface AparelhosRepository extends JpaRepository<AparelhoEntity, Long>{

}
