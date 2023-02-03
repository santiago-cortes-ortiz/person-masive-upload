package com.jeisson.masiveupload.service;

import com.jeisson.masiveupload.model.Person;
import com.jeisson.masiveupload.service.gateways.CsvReadGateway;
import com.jeisson.masiveupload.service.gateways.JdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final CsvReadGateway csvReadGateway;
    private final JdbcRepository jdbcRepository;

    public List<Person> readCsv(MultipartFile file) {
        return csvReadGateway.readCsv(file);
    }

    public void savePerson(MultipartFile file) {
        var data = csvReadGateway.readCsv(file);
        if (data.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        jdbcRepository.savePerson(data);
    }

    public void saveWithoutBatch(MultipartFile file) {
        var data = csvReadGateway.readCsv(file);
        if (data.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        jdbcRepository.saveWithoutBatch(data);
    }

}
