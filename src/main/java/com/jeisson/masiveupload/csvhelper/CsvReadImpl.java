package com.jeisson.masiveupload.csvhelper;

import com.jeisson.masiveupload.model.Person;
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
    public List<Person> readCsv(MultipartFile file) {
        long startTime = System.currentTimeMillis();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            var parser = new CSVParser(reader,
                    CSVFormat.DEFAULT);
            List<Person> rows = new ArrayList<>();
            for (CSVRecord record : parser) {
                //continue if header row
                if (record.getRecordNumber() == 1) {
                    continue;
                }
                rows.add(new Person(record.get(0), record.get(1), record.get(2)));
            }
            parser.close();
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            double totalTimeInSeconds = totalTime / 1000.0;
            System.out.println("El proceso tardó: " + totalTimeInSeconds + " segundos");
            return rows;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Object[]> readCsv2(MultipartFile file) {
        long startTime = System.currentTimeMillis();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            var parser = new CSVParser(reader,
                    CSVFormat.DEFAULT);
            List<Object[]> rows = new ArrayList<>();
            for (CSVRecord record : parser) {
                //continue if header row
                if (record.getRecordNumber() == 1) {
                    continue;
                }
                rows.add(new Object[]{record.get(0), record.get(1), record.get(2)});
            }
            parser.close();
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            double totalTimeInSeconds = totalTime / 1000.0;
            System.out.println("El proceso tardó: " + totalTimeInSeconds + " segundos");
            return rows;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
