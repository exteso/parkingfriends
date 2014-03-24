package com.exteso.lab.pf.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.exteso.lab.pf.domain.ParkPlaceGroup;
import com.exteso.lab.pf.repository.ParkPlaceGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing ParkPlaceGroup.
 */
@RestController
@RequestMapping("/app")
public class ParkPlaceGroupResource {

    private final Logger log = LoggerFactory.getLogger(ParkPlaceGroupResource.class);

    @Inject
    private ParkPlaceGroupRepository parkplacegroupRepository;

    /**
     * POST  /rest/parkplacegroups -> Create a new parkplacegroup.
     */
    @RequestMapping(value = "/rest/parkplacegroups",
            method = RequestMethod.POST,
            produces = "application/json")
    @Timed
    public void create(@RequestBody ParkPlaceGroup parkplacegroup) {
        log.debug("REST request to save ParkPlaceGroup : {}", parkplacegroup);
        parkplacegroupRepository.save(parkplacegroup);
    }

    /**
     * GET  /rest/parkplacegroups -> get all the parkplacegroups.
     */
    @RequestMapping(value = "/rest/parkplacegroups",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public List<ParkPlaceGroup> getAll() {
        log.debug("REST request to get all ParkPlaceGroups");
        return parkplacegroupRepository.findAll();
    }

    /**
     * GET  /rest/parkplacegroups/:id -> get the "id" parkplacegroup.
     */
    @RequestMapping(value = "/rest/parkplacegroups/{id}",
            method = RequestMethod.GET,
            produces = "application/json")
    @Timed
    public ParkPlaceGroup get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get ParkPlaceGroup : {}", id);
        ParkPlaceGroup parkplacegroup = parkplacegroupRepository.findOne(id);
        if (parkplacegroup == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return parkplacegroup;
    }

    /**
     * DELETE  /rest/parkplacegroups/:id -> delete the "id" parkplacegroup.
     */
    @RequestMapping(value = "/rest/parkplacegroups/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json")
    @Timed
    public void delete(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to delete ParkPlaceGroup : {}", id);
        parkplacegroupRepository.delete(id);
    }
}
