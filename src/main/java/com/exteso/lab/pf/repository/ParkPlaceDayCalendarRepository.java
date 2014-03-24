package com.exteso.lab.pf.repository;

import com.exteso.lab.pf.domain.ParkPlaceDayCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the ParkPlaceDayCalendar entity.
 */
public interface ParkPlaceDayCalendarRepository extends JpaRepository<ParkPlaceDayCalendar, Long> {

}
