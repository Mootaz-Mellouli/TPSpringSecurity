package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.Compte;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long>{


}
