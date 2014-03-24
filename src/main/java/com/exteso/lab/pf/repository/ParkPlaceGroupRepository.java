package com.exteso.lab.pf.repository;

import com.exteso.lab.pf.domain.ParkPlaceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ParkPlaceGroup entity.
 */
public interface ParkPlaceGroupRepository extends JpaRepository<ParkPlaceGroup, Long> {

}
