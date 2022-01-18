package pl.sop.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.sop.dto.WorkScheduleDTO;
import pl.sop.services.WorkScheduleService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class WorkScheduleControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private WorkScheduleService workScheduleService;

    @Before
    public void setup() {
        WorkScheduleController workScheduleController = new WorkScheduleController();
        workScheduleController.workScheduleService = workScheduleService;
        mockMvc = MockMvcBuilders.standaloneSetup(workScheduleController).build();
    }

    @Test
    public void when_get_all_work_schedules_should_return_ok() throws Exception {
        List<WorkScheduleDTO> mockWorkSchedules = new ArrayList<>();
        mockWorkSchedules.add(new WorkScheduleDTO());
        when(workScheduleService.getAllWorkSchedules()).thenReturn(mockWorkSchedules);
        mockMvc.perform(get("/api/planner/workSchedule"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockWorkSchedules)));
    }

    @Test
    public void when_create_work_schedule_should_return_ok() throws Exception {
        WorkScheduleDTO mockWorkSchedule = new WorkScheduleDTO();
        when(workScheduleService.createWorkSchedule(Mockito.any())).thenReturn(new ArrayList<>());
        mockMvc.perform(post("/api/planner/workSchedule")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockWorkSchedule)))
                .andExpect(status().isOk());
    }

    //TODO: Why return 500?
//    @Test
//    public void when_delete_work_schedule_should_return_ok() throws Exception {
//        mockMvc.perform(delete("/api/planner/workSchedule/{id}", 1L))
//                .andExpect(status().isOk());
//    }

    @Test
    public void when_delete_work_schedule_no_id_should_return_error() throws Exception {
        mockMvc.perform(delete("/api/planner/workSchedule"))
                .andExpect(status().is4xxClientError());
    }
}