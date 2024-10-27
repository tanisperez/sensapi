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
        jdbcTemplate.execute("DROP SCHEMA IF EXISTS SENSAPI CASCADE");
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS SENSAPI");
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

    @Test
    @DisplayName("find first page of sensors")
    void find_first_page() {
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:14:22:01:23:45', 'Sensor_Temperatura_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:16:36:02:46:58', 'Sensor_Humedad_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:18:64:03:59:69', 'Sensor_Presion_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1A:79:04:12:89', 'Sensor_Temperatura_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1C:85:05:25:92', 'Sensor_Humedad_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1E:48:06:33:14', 'Sensor_Presion_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:20:7B:07:44:27', 'Sensor_Temperatura_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:22:4D:08:57:38', 'Sensor_Humedad_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:24:13:09:67:41', 'Sensor_Presion_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:26:85:10:79:52', 'Sensor_Temperatura_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:28:93:11:81:66', 'Sensor_Humedad_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2A:57:12:94:72', 'Sensor_Presion_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2C:63:13:16:85', 'Sensor_Temperatura_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2E:4A:14:28:98', 'Sensor_Humedad_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:30:5F:15:39:10', 'Sensor_Presion_5')");

        PageRequest pageRequest = new PageRequest(1, 10);
        SensorRepository sensorRepository = new SensorRepositoryImpl(jdbcTemplate);

        Page<Sensor> results = sensorRepository.findSensors(pageRequest);

        assertThat(results).isNotNull();
    }

}