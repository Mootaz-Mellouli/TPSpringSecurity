package com.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.entity.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
