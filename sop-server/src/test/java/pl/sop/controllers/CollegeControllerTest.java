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
import pl.sop.dto.ActivationKeyDTO;
import pl.sop.dto.CollegeDTO;
import pl.sop.dto.CollegeRegistrationDTO;
import pl.sop.entities.organizationStructure.College;
import pl.sop.entities.organizationStructure.CollegeService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CollegeControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private CollegeService collegeService;

    @Before
    public void setup() {
        CollegeController collegeController = new CollegeController();
        collegeController.collegeService = collegeService;
        mockMvc = MockMvcBuilders.standaloneSetup(collegeController).build();
    }

    @Test
    public void when_get_all_colleges_should_return_ok() throws Exception {
        List<CollegeDTO> mockColleges = new ArrayList<>();
        mockColleges.add(new CollegeDTO());
        when(collegeService.findAllColleges()).thenReturn(mockColleges);
        mockMvc.perform(get("/api/colleges"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_get_college_by_id_should_return_ok() throws Exception {
        CollegeDTO mockCollege = new CollegeDTO();
        when(collegeService.findCollegeById(Mockito.any())).thenReturn(mockCollege);
        mockMvc.perform(get("/api/colleges/{id}", 0L))
                .andExpect(status().isOk());
    }

    @Test
    public void when_get_all_available_colleges_should_return_ok() throws Exception {
        List<CollegeDTO> mockColleges = new ArrayList<>();
        mockColleges.add(new CollegeDTO());
        when(collegeService.findAllAvailableColleges()).thenReturn(mockColleges);
        mockMvc.perform(get("/api/available-colleges", 0L))
                .andExpect(status().isOk());
    }

    @Test
    public void when_change_college_active_status_should_return_ok() throws Exception {
        mockMvc.perform(post("/api/colleges/change-active-status/{collegeId}/{newStatus}", "1", true))
                .andExpect(status().isOk());
    }

    @Test
    public void when_create_new_college_should_return_ok() throws Exception {
        mockMvc.perform(post("/api/colleges/createNew")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CollegeDTO())))
                .andExpect(status().isOk());
    }

    //TODO: Error
//    @Test
//    public void when_update_college_should_return_ok() throws Exception {
//        mockMvc.perform(post("/api/colleges/update")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(new CollegeDTO())))
//                .andExpect(status().isOk());
//    }

    @Test
    public void when_activate_college_should_return_ok() throws Exception {
        mockMvc.perform(post("/api/colleges/activate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ActivationKeyDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void when_register_college_should_return_ok() throws Exception {
        mockMvc.perform(post("/api/colleges/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new CollegeRegistrationDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void when_delete_college_should_return_ok() throws Exception {
        mockMvc.perform(delete("/api/colleges")
                        .param("collegeId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_delete_college_structure_should_return_ok() throws Exception {
        mockMvc.perform(delete("/api/college-structure")
                        .param("collegeStructureId", "1")
                        .param("collegeStructure", "structure"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_update_college_should_return_ok() throws Exception {
        mockMvc.perform(put("/api/college/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new College())))
                .andExpect(status().isOk());
    }
}