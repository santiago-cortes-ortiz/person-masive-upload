package com.jeisson.masiveupload.service.gateways;

import com.jeisson.masiveupload.model.Person;

import java.util.List;

public interface JdbcRepository {
    void savePerson(List<Person> data);

    void saveWithoutBatch(List<Person> data);
}
