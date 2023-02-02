package com.jeisson.masiveupload.csvhelper;

import com.jeisson.masiveupload.service.gateways.CsvReadGateway;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvReadImpl implements CsvReadGateway {
    @Override
    public List<String[]> readCsv(MultipartFile file) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            var parser = new CSVParser(reader,
                    CSVFormat.DEFAULT);
            List<String[]> rows = new ArrayList<>();
            for (CSVRecord record : parser) {
                //continue if header row
                if (record.getRecordNumber() == 1) {
                    continue;
                }
                rows.add(record.stream().toArray(String[]::new));
            }
            parser.close();
            return rows;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
