package com.jeisson.masiveupload;

import com.jeisson.masiveupload.model.Person;
import com.jeisson.masiveupload.service.PersonService;
import com.jeisson.masiveupload.service.gateways.CsvReadGateway;
import com.jeisson.masiveupload.service.gateways.JdbcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @MockBean
    private CsvReadGateway csvReadGateway;

    @MockBean
    private JdbcRepository jdbcRepository;

    @Test
    public void testSavePerson() {
        //prepare
        var fileMock = new MockMultipartFile("file", "test.csv", "text/plain",
                "".getBytes());
        var data = List.of(new Person("John ", "Leonel", "123456789"));
        when(csvReadGateway.readCsv(fileMock)).thenReturn(data);
        doNothing().when(jdbcRepository).savePerson(data);
        //execute
        personService.savePerson(fileMock);
        //assert
        verify(csvReadGateway).readCsv(fileMock);
        verify(jdbcRepository).savePerson(data);
    }

    @Test
    public void testSavePersonException() {
        //prepare
        var fileMock = new MockMultipartFile("file", "test.csv", "text/plain",
                "".getBytes());
        when(csvReadGateway.readCsv(fileMock))
                .thenReturn(List.of());
        assertThrows(RuntimeException.class,
                () -> personService.savePerson(fileMock));
    }
}
