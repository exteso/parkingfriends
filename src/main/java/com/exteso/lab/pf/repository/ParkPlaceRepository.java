package com.exteso.lab.pf.repository;

import com.exteso.lab.pf.domain.ParkPlace;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ParkPlace entity.
 */
public interface ParkPlaceRepository extends JpaRepository<ParkPlace, Long> {

}
