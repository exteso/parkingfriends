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
import com.exteso.lab.pf.domain.ParkPlaceDayCalendar;
import com.exteso.lab.pf.repository.ParkPlaceDayCalendarRepository;


/**
 * Test class for the ParkPlaceDayCalendarResource REST controller.
 *
 * @see ParkPlaceDayCalendarResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class })
@ActiveProfiles("dev")
public class ParkPlaceDayCalendarResourceTest {
	
    private static final Long DEFAULT_ID = new Long(1L);

    private static final LocalDate DEFAULT_SAMPLE_DATE_ATTR = new LocalDate(0L);

    private static final LocalDate UPD_SAMPLE_DATE_ATTR = new LocalDate();

    private static final String DEFAULT_SAMPLE_TEXT_ATTR = "sampleTextAttribute";

    private static final String UPD_SAMPLE_TEXT_ATTR = "sampleTextAttributeUpt";

    @Inject
    private ParkPlaceDayCalendarRepository parkplacedaycalendarRepository;

    private MockMvc restParkPlaceDayCalendarMockMvc;
    
    private ParkPlaceDayCalendar parkplacedaycalendar;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ParkPlaceDayCalendarResource parkplacedaycalendarResource = new ParkPlaceDayCalendarResource();
        ReflectionTestUtils.setField(parkplacedaycalendarResource, "parkplacedaycalendarRepository", parkplacedaycalendarRepository);

        this.restParkPlaceDayCalendarMockMvc = MockMvcBuilders.standaloneSetup(parkplacedaycalendarResource).build();

        parkplacedaycalendar = new ParkPlaceDayCalendar();
        parkplacedaycalendar.setDay(DEFAULT_SAMPLE_DATE_ATTR);
        parkplacedaycalendar.setId(DEFAULT_ID);
    }

    @Test
    public void testCRUDParkPlaceDayCalendar() throws Exception {

    	// Create ParkPlaceDayCalendar
    	restParkPlaceDayCalendarMockMvc.perform(post("/app/rest/parkplacedaycalendars")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(parkplacedaycalendar)))
                .andExpect(status().isOk());

    	// Read ParkPlaceDayCalendar
    	restParkPlaceDayCalendarMockMvc.perform(get("/app/rest/parkplacedaycalendars/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
    			.andExpect(jsonPath("$.day").value(DEFAULT_SAMPLE_DATE_ATTR.toString()));

    	// Update ParkPlaceDayCalendar
    	restParkPlaceDayCalendarMockMvc.perform(post("/app/rest/parkplacedaycalendars")
    			.contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(parkplacedaycalendar)))
                .andExpect(status().isOk());

    	// Read updated ParkPlaceDayCalendar
    	restParkPlaceDayCalendarMockMvc.perform(get("/app/rest/parkplacedaycalendars/{id}", DEFAULT_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(DEFAULT_ID.intValue()))
    			.andExpect(jsonPath("$.day").value(DEFAULT_SAMPLE_DATE_ATTR.toString()));

    	// Delete ParkPlaceDayCalendar
    	restParkPlaceDayCalendarMockMvc.perform(delete("/app/rest/parkplacedaycalendars/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    	// Read nonexisting ParkPlaceDayCalendar
    	restParkPlaceDayCalendarMockMvc.perform(get("/app/rest/parkplacedaycalendars/{id}", DEFAULT_ID)
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

    }
}
