package com.supply.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supply.users.models.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
