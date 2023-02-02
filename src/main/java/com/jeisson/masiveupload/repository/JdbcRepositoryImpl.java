package com.jeisson.masiveupload.repository;

import com.jeisson.masiveupload.service.gateways.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRepositoryImpl implements JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void savePerson(List<String[]> data) {
        try {
            List<Object[]> objects = new ArrayList<>();
            for (String[] person : data) {
                objects.add(new Object[]{person[0], person[1], person[2]});
            }
            jdbcTemplate.batchUpdate("INSERT INTO tbl_person (name, lastname, phone) VALUES (?, ?, ?)", objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveWithoutBatch(List<String[]> data) {
        try {
            for (String[] person : data) {
                jdbcTemplate.update("INSERT INTO tbl_person (name, lastname, phone) VALUES (?, ?, ?)", person[0], person[1], person[2]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
