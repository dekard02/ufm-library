package com.ufm.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufm.library.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
