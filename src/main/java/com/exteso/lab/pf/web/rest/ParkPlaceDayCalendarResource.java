package com.exteso.lab.pf.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.exteso.lab.pf.domain.ParkPlaceDayCalendar;
import com.exteso.lab.pf.repository.ParkPlaceDayCalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing ParkPlaceDayCalendar.
 */
@RestController
@RequestMapping("/app")
public class ParkPlaceDayCalendarResource {

    private final Logger log = LoggerFactory.getLogger(ParkPlaceDayCalendarResource.class);

    @Inject
    private ParkPlaceDayCalendarRepository parkplacedaycalendarRepository;

    /**
     * POST  /rest/parkplacedaycalendars -> Create a new parkplacedaycalendar.
     */
    @RequestMapping(value = "/rest/parkplacedaycalendars",
            method = RequestMethod.POST,
            produces = "application/json")
    @Timed
    public void create(@RequestBody ParkPlaceDayCalendar parkplacedaycalendar) {
        log.debug("REST request to save ParkPlaceDayCalendar : {}", parkplacedaycalendar);
        parkplacedaycalendarRepository.save(parkplacedaycalendar);
    }

    /**
     * GET  /rest/parkplacedaycalendars -> get all the parkplacedaycalendars.
     */
    @RequestMapping(value = "/rest/parkplacedaycalendars",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public List<ParkPlaceDayCalendar> getAll() {
        log.debug("REST request to get all ParkPlaceDayCalendars");
        return parkplacedaycalendarRepository.findAll();
    }

    /**
     * GET  /rest/parkplacedaycalendars/:id -> get the "id" parkplacedaycalendar.
     */
    @RequestMapping(value = "/rest/parkplacedaycalendars/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public ParkPlaceDayCalendar get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ParkPlaceDayCalendar : {}", id);
        ParkPlaceDayCalendar parkplacedaycalendar = parkplacedaycalendarRepository.findOne(id);
        if (parkplacedaycalendar == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return parkplacedaycalendar;
    }

    /**
     * DELETE  /rest/parkplacedaycalendars/:id -> delete the "id" parkplacedaycalendar.
     */
    @RequestMapping(value = "/rest/parkplacedaycalendars/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @Timed
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to delete ParkPlaceDayCalendar : {}", id);
        parkplacedaycalendarRepository.delete(id);
    }
}
