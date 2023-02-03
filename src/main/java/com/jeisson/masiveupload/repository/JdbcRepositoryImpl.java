package com.jeisson.masiveupload.repository;

import com.jeisson.masiveupload.model.Person;
import com.jeisson.masiveupload.service.gateways.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class JdbcRepositoryImpl implements JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void savePerson(List<Person> data) {
        long startTime = System.currentTimeMillis();
        try {
            List<Object[]> objects = data
                    .stream()
                    .map(person -> new Object[]{person.name(), person.lastName(), person.phone()}).toList();
            jdbcTemplate.batchUpdate("INSERT INTO tbl_person (name, lastname, phone) VALUES (?, ?, ?)", objects);
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            double totalTimeInSeconds = totalTime / 1000.0;
            System.out.println("El proceso tardó escribir: " + totalTimeInSeconds + " segundos");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void saveWithoutBatch(List<Person> data) {
        try {
            data.forEach(person -> jdbcTemplate.update("INSERT INTO tbl_person (name, lastname, phone) VALUES (?, ?, ?)",
                    person.name(), person.lastName(), person.phone()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveWithoutBatch2(List<Object[]> data) {
        long startTime = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO tbl_person (name, lastname, phone) VALUES (?, ?, ?)", data);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        double totalTimeInSeconds = totalTime / 1000.0;
        System.out.println("El proceso tardó en escribir: " + totalTimeInSeconds + " segundos");
    }

}
