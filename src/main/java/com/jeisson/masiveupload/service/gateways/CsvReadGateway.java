package com.jeisson.masiveupload.service.gateways;

import com.jeisson.masiveupload.model.Person;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvReadGateway {
    List<Person> readCsv(MultipartFile file);

    List<Object[]> readCsv2(MultipartFile file);
}
