package com.exteso.lab.pf.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import com.exteso.lab.pf.domain.ValidityInterval;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.exteso.lab.pf.Application;
import com.exteso.lab.pf.domain.ParkPlaceGroup;
import com.exteso.lab.pf.repository.ParkPlaceGroupRepository;


/**
 * Test class for the ParkPlaceGroupResource REST controller.
 *
 * @see ParkPlaceGroupResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("dev")
public class ParkPlaceGroupResourceTest {
	
    private static final Long DEFAULT_ID = 1L;

    private static final LocalDate DEFAULT_SAMPLE_DATE_ATTR = new LocalDate(0L);

    private static final LocalDate UPD_SAMPLE_DATE_ATTR = new LocalDate();

    private static final String DEFAULT_SAMPLE_TEXT_ATTR = "sampleTextAttribute";

    private static final String UPD_SAMPLE_TEXT_ATTR = "sampleTextAttributeUpt";

    @Inject
    private ParkPlaceGroupRepository parkplacegroupRepository;

    private MockMvc restParkPlaceGroupMockMvc;
    
    private ParkPlaceGroup parkplacegroup;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ParkPlaceGroupResource parkplacegroupResource = new ParkPlaceGroupResource();
        ReflectionTestUtils.setField(parkplacegroupResource, "parkplacegroupRepository", parkplacegroupRepository);

        this.restParkPlaceGroupMockMvc = MockMvcBuilders.standaloneSetup(parkplacegroupResource).build();

        parkplacegroup = new ParkPlaceGroup();
        parkplacegroup.setId(DEFAULT_ID);
        final ValidityInterval validityInterval = new ValidityInterval();
        validityInterval.setValidFrom(DEFAULT_SAMPLE_DATE_ATTR);
        parkplacegroup.setValidityInterval(validityInterval);
    	parkplacegroup.setName(DEFAULT_SAMPLE_TEXT_ATTR);
    }

    @Test
    @Ignore
    public void testCRUDParkPlaceGroup() throws Exception {

    	// Create ParkPlaceGroup
    	restParkPlaceGroupMockMvc.perform(post("/app/rest/parkplacegroups")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(parkplacegroup)))
                .andExpect(status().isOk());

    	// Read ParkPlaceGroup
    	restParkPlaceGroupMockMvc.perform(get("/app/rest/parkplacegroups/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
    			.andExpect(jsonPath("$.day").value(DEFAULT_SAMPLE_DATE_ATTR.toString()))
    			.andExpect(jsonPath("$.sampleTextAttribute").value(DEFAULT_SAMPLE_TEXT_ATTR));

    	// Update ParkPlaceGroup
        final ValidityInterval validityInterval = new ValidityInterval();
        validityInterval.setValidFrom(UPD_SAMPLE_DATE_ATTR);
        parkplacegroup.setValidityInterval(validityInterval);
    	parkplacegroup.setName(UPD_SAMPLE_TEXT_ATTR);
  
    	restParkPlaceGroupMockMvc.perform(post("/app/rest/parkplacegroups")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(parkplacegroup)))
                .andExpect(status().isOk());

    	// Read updated ParkPlaceGroup
    	restParkPlaceGroupMockMvc.perform(get("/app/rest/parkplacegroups/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
    			.andExpect(jsonPath("$.day").value(UPD_SAMPLE_DATE_ATTR.toString()))
    			.andExpect(jsonPath("$.sampleTextAttribute").value(UPD_SAMPLE_TEXT_ATTR));

    	// Delete ParkPlaceGroup
    	restParkPlaceGroupMockMvc.perform(delete("/app/rest/parkplacegroups/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    	// Read nonexisting ParkPlaceGroup
    	restParkPlaceGroupMockMvc.perform(get("/app/rest/parkplacegroups/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
