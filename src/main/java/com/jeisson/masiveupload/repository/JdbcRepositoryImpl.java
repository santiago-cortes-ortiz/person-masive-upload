package com.jeisson.masiveupload.repository;

import com.jeisson.masiveupload.model.Person;
import com.jeisson.masiveupload.service.gateways.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcRepositoryImpl implements JdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void savePerson(List<Person> data) {
        try {
            List<Object[]> objects = new ArrayList<>();
            for (Person person : data) {
                objects.add(new Object[]{person.name(), person.lastName(), person.phone()});
            }
            jdbcTemplate.batchUpdate("INSERT INTO tbl_person (name, lastname, phone) VALUES (?, ?, ?)", objects);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void saveWithoutBatch(List<Person> data) {
        try {
            for (Person person : data) {
                jdbcTemplate.update("INSERT INTO tbl_person (name, lastname, phone) VALUES (?, ?, ?)",
                        person.name(), person.lastName(), person.phone());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
