package com.exteso.lab.pf.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
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
import com.exteso.lab.pf.domain.ParkPlace;
import com.exteso.lab.pf.repository.ParkPlaceRepository;


/**
 * Test class for the ParkPlaceResource REST controller.
 *
 * @see ParkPlaceResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("dev")
public class ParkPlaceResourceTest {
	
    private static final Long DEFAULT_ID = new Long(1L);

    private static final LocalDate DEFAULT_SAMPLE_DATE_ATTR = new LocalDate(0L);

    private static final LocalDate UPD_SAMPLE_DATE_ATTR = new LocalDate();

    private static final String DEFAULT_SAMPLE_TEXT_ATTR = "sampleTextAttribute";

    private static final String UPD_SAMPLE_TEXT_ATTR = "sampleTextAttributeUpt";

    @Inject
    private ParkPlaceRepository parkplaceRepository;

    private MockMvc restParkPlaceMockMvc;
    
    private ParkPlace parkplace;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ParkPlaceResource parkplaceResource = new ParkPlaceResource();
        ReflectionTestUtils.setField(parkplaceResource, "parkplaceRepository", parkplaceRepository);

        this.restParkPlaceMockMvc = MockMvcBuilders.standaloneSetup(parkplaceResource).build();

        parkplace = new ParkPlace();
        parkplace.setId(DEFAULT_ID);
    	parkplace.setSampleDateAttribute(DEFAULT_SAMPLE_DATE_ATTR);
    	parkplace.setSampleTextAttribute(DEFAULT_SAMPLE_TEXT_ATTR);
    }

    @Test
    public void testCRUDParkPlace() throws Exception {

    	// Create ParkPlace
    	restParkPlaceMockMvc.perform(post("/app/rest/parkplaces")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(parkplace)))
                .andExpect(status().isOk());

    	// Read ParkPlace
    	restParkPlaceMockMvc.perform(get("/app/rest/parkplaces/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
    			.andExpect(jsonPath("$.sampleDateAttribute").value(DEFAULT_SAMPLE_DATE_ATTR.toString()))
    			.andExpect(jsonPath("$.sampleTextAttribute").value(DEFAULT_SAMPLE_TEXT_ATTR));

    	// Update ParkPlace
    	parkplace.setSampleDateAttribute(UPD_SAMPLE_DATE_ATTR);
    	parkplace.setSampleTextAttribute(UPD_SAMPLE_TEXT_ATTR);
  
    	restParkPlaceMockMvc.perform(post("/app/rest/parkplaces")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(parkplace)))
                .andExpect(status().isOk());

    	// Read updated ParkPlace
    	restParkPlaceMockMvc.perform(get("/app/rest/parkplaces/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
    			.andExpect(jsonPath("$.sampleDateAttribute").value(UPD_SAMPLE_DATE_ATTR.toString()))
    			.andExpect(jsonPath("$.sampleTextAttribute").value(UPD_SAMPLE_TEXT_ATTR));

    	// Delete ParkPlace
    	restParkPlaceMockMvc.perform(delete("/app/rest/parkplaces/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    	// Read nonexisting ParkPlace
    	restParkPlaceMockMvc.perform(get("/app/rest/parkplaces/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
