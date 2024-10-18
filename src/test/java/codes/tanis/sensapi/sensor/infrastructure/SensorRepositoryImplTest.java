package codes.tanis.sensapi.sensor.infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class SensorRepositoryImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("find first page of sensors")
    void find_first_page_of_sensors() {
        System.out.println("Test");
    }

}