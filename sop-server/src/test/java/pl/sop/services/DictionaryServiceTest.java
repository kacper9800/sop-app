package pl.sop.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import pl.sop.dto.DictionaryDTO;
import pl.sop.entities.Dictionary;
import pl.sop.enums.EDictionaryType;
import pl.sop.repositories.DictionaryRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class DictionaryServiceTest {

    @Mock
    DictionaryRepository dictionaryRepository;

    @InjectMocks
    DictionaryService dictionaryService;

    @Test
    public void when_get_all_study_modes_return_dictionary_dto_list() {
        List<Dictionary> mockDictionaries = new ArrayList<>();
        Dictionary dictionary = new Dictionary();
        dictionary.setName("Dictionary");
        dictionary.setDictionaryType(EDictionaryType.STUDY_MODE);
        mockDictionaries.add(dictionary);
        Mockito.when(dictionaryRepository.getAllStudyModes()).thenReturn(mockDictionaries);
        ResponseEntity<List<DictionaryDTO>> response = dictionaryService.getAllStudyModes();
        assertEquals(response.getBody().size(), mockDictionaries.size());
        assertEquals(response.getBody().get(0).getDictionaryType(), mockDictionaries.get(0).getDictionaryType().name());
    }

    @Test
    public void when_get_all_sex_types_return_dictionary_dto_list() {
        List<Dictionary> mockDictionaries = new ArrayList<>();
        Dictionary dictionary = new Dictionary();
        dictionary.setName("Dictionary");
        dictionary.setDictionaryType(EDictionaryType.SEX_TYPE);
        mockDictionaries.add(dictionary);
        Mockito.when(dictionaryRepository.getAllSexTypes()).thenReturn(mockDictionaries);
        ResponseEntity<List<DictionaryDTO>> response = dictionaryService.getAllSexTypes();
        assertEquals(response.getBody().size(), mockDictionaries.size());
        assertEquals(response.getBody().get(0).getDictionaryType(), mockDictionaries.get(0).getDictionaryType().name());
    }

    @Test
    public void when_get_all_academic_degrees_return_dictionary_dto_list() {
        List<Dictionary> mockDictionaries = new ArrayList<>();
        Dictionary dictionary = new Dictionary();
        dictionary.setName("Dictionary");
        dictionary.setDictionaryType(EDictionaryType.ACADEMIC_DEGREE);
        mockDictionaries.add(dictionary);
        Mockito.when(dictionaryRepository.getAllAcademicDegrees()).thenReturn(mockDictionaries);
        ResponseEntity<List<DictionaryDTO>> response = dictionaryService.getAllAcademicDegrees();
        assertEquals(response.getBody().size(), mockDictionaries.size());
        assertEquals(response.getBody().get(0).getDictionaryType(), mockDictionaries.get(0).getDictionaryType().name());
    }

    @Test
    public void when_get_all_request_decision_return_dictionary_dto_list() {
        List<Dictionary> mockDictionaries = new ArrayList<>();
        Dictionary dictionary = new Dictionary();
        dictionary.setName("Dictionary");
        dictionary.setDictionaryType(EDictionaryType.REQUEST_DECISION_STATUS);
        mockDictionaries.add(dictionary);
        //TODO: Why lenient is necessary ?
        Mockito.lenient().when(dictionaryRepository.getAllRequestDecisionStatuses()).thenReturn(mockDictionaries);
        ResponseEntity<List<DictionaryDTO>> response = dictionaryService.getAllRequestDecisionStatuses();
        assertEquals(response.getBody().size(), mockDictionaries.size() * 2);
        assertEquals(response.getBody().get(0).getValue(),  "ACCEPTED");
        assertEquals(response.getBody().get(1).getValue(),  "REJECTED");
    }

    @Test
    public void when_get_all_request_types_return_dictionary_dto_list() {
        List<Dictionary> mockDictionaries = new ArrayList<>();
        Dictionary dictionary = new Dictionary();
        dictionary.setName("Dictionary");
        dictionary.setDictionaryType(EDictionaryType.REQUEST_TYPE);
        mockDictionaries.add(dictionary);
        Mockito.when(dictionaryRepository.getAllRequestTypes()).thenReturn(mockDictionaries);
        ResponseEntity<List<DictionaryDTO>> response = dictionaryService.getAllRequestTypes();
        assertEquals(response.getBody().size(), mockDictionaries.size());
        assertEquals(response.getBody().get(0).getDictionaryType(), mockDictionaries.get(0).getDictionaryType().name());
    }

    @Test
    public void when_get_all_by_value_return_dictionary() {
        Dictionary mockDictionary = new Dictionary();
        mockDictionary.setName("Dictionary");
        mockDictionary.setDictionaryType(EDictionaryType.REQUEST_TYPE);
        Mockito.when(dictionaryRepository.getByValue(Mockito.any())).thenReturn(mockDictionary);
        Dictionary response = dictionaryService.getByValue("");
        assertEquals(response.getName(), mockDictionary.getName());
    }
}