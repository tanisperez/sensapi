package codes.tanis.sensapi.sensor.infrastructure;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.common.domain.PageRequest;
import codes.tanis.sensapi.sensor.domain.Sensor;
import codes.tanis.sensapi.sensor.domain.SensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class SensorRepositoryImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate.execute("DROP SCHEMA IF EXISTS sensapi CASCADE");
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS sensapi");
        jdbcTemplate.execute("""
            CREATE TABLE SENSAPI.SENSOR (
                mac CHAR(17) PRIMARY KEY,
                name VARCHAR(30) NOT NULL,
                registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )"""
        );
    }

    @Test
    @DisplayName("find no results")
    void find_first_page_of_sensors() {
        PageRequest pageRequest = new PageRequest(1, 10);
        SensorRepository sensorRepository = new SensorRepositoryImpl(jdbcTemplate);

        Page<Sensor> results = sensorRepository.findSensors(pageRequest);

        assertThat(results).isNotNull();
        assertThat(results.content().size()).isZero();
        assertThat(results.pageRequest()).isEqualTo(pageRequest);
        assertThat(results.totalElements()).isEqualTo(0);
        assertThat(results.totalPages()).isEqualTo(0);
    }

    // @Test
    @DisplayName("find first page of sensors")
    void another_test() {
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (MAC, NAME) VALUES ('3A:45:9B:2C:18', 'ESP-32 Patio')");

        PageRequest pageRequest = new PageRequest(1, 10);
        SensorRepository sensorRepository = new SensorRepositoryImpl(jdbcTemplate);

        Page<Sensor> results = sensorRepository.findSensors(pageRequest);

        assertThat(results).isNotNull();
    }

}