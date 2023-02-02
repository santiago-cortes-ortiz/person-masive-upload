package com.jeisson.masiveupload.service.gateways;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CsvReadGateway {
    List<String[]> readCsv(MultipartFile file);
}
