package com.exteso.lab.pf.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.exteso.lab.pf.domain.ParkPlace;
import com.exteso.lab.pf.repository.ParkPlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing ParkPlace.
 */
@RestController
@RequestMapping("/app")
public class ParkPlaceResource {

    private final Logger log = LoggerFactory.getLogger(ParkPlaceResource.class);

    @Inject
    private ParkPlaceRepository parkplaceRepository;

    /**
     * POST  /rest/parkplaces -> Create a new parkplace.
     */
    @RequestMapping(value = "/rest/parkplaces",
            method = RequestMethod.POST,
            produces = "application/json")
    @Timed
    public void create(@RequestBody ParkPlace parkplace) {
        log.debug("REST request to save ParkPlace : {}", parkplace);
        parkplaceRepository.save(parkplace);
    }

    /**
     * GET  /rest/parkplaces -> get all the parkplaces.
     */
    @RequestMapping(value = "/rest/parkplaces",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public List<ParkPlace> getAll() {
        log.debug("REST request to get all ParkPlaces");
        return parkplaceRepository.findAll();
    }

    /**
     * GET  /rest/parkplaces/:id -> get the "id" parkplace.
     */
    @RequestMapping(value = "/rest/parkplaces/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public ParkPlace get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ParkPlace : {}", id);
        ParkPlace parkplace = parkplaceRepository.findOne(id);
        if (parkplace == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return parkplace;
    }

    /**
     * DELETE  /rest/parkplaces/:id -> delete the "id" parkplace.
     */
    @RequestMapping(value = "/rest/parkplaces/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @Timed
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to delete ParkPlace : {}", id);
        parkplaceRepository.delete(id);
    }
}
