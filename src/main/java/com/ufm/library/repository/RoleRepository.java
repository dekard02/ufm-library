package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.ufm.library.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>,
        QuerydslPredicateExecutor<Role> {

}
