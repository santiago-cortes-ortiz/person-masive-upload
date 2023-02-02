package com.jeisson.masiveupload.service.gateways;

import java.util.List;

public interface JdbcRepository {
    void savePerson(List<String[]> data);

    void saveWithoutBatch(List<String[]> data);
}
