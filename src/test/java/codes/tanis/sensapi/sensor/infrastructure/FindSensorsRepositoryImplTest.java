package codes.tanis.sensapi.sensor.infrastructure;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.common.domain.PageRequest;
import codes.tanis.sensapi.sensor.domain.FindSensorsRepository;
import codes.tanis.sensapi.sensor.domain.Mac;
import codes.tanis.sensapi.sensor.domain.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JdbcTest
class FindSensorsRepositoryImplTest {

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
        FindSensorsRepository findSensorsRepository = new FindSensorsRepositoryImpl(jdbcTemplate);

        Page<Sensor> results = findSensorsRepository.findSensors(pageRequest);

        assertThat(results).isNotNull();
        assertThat(results.content().size()).isZero();
        assertThat(results.pageRequest()).isEqualTo(pageRequest);
        assertThat(results.totalElements()).isEqualTo(0);
        assertThat(results.totalPages()).isEqualTo(0);
    }

    @Test
    @DisplayName("find first page of sensors")
    void find_first_page() {
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:14:22:01:23:45', 'Sensor_Temperature_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:16:36:02:46:58', 'Sensor_Humidity_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:18:64:03:59:69', 'Sensor_Pressure_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1A:79:04:12:89', 'Sensor_Temperature_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1C:85:05:25:92', 'Sensor_Humidity_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1E:48:06:33:14', 'Sensor_Pressure_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:20:7B:07:44:27', 'Sensor_Temperature_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:22:4D:08:57:38', 'Sensor_Humidity_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:24:13:09:67:41', 'Sensor_Pressure_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:26:85:10:79:52', 'Sensor_Temperature_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:28:93:11:81:66', 'Sensor_Humidity_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2A:57:12:94:72', 'Sensor_Pressure_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2C:63:13:16:85', 'Sensor_Temperature_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2E:4A:14:28:98', 'Sensor_Humidity_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:30:5F:15:39:10', 'Sensor_Pressure_5')");
        PageRequest pageRequest = new PageRequest(1, 10);
        FindSensorsRepository findSensorsRepository = new FindSensorsRepositoryImpl(jdbcTemplate);

        Page<Sensor> results = findSensorsRepository.findSensors(pageRequest);

        assertThat(results).isNotNull();
        assertThat(results.content().size()).isEqualTo(10);
        assertThat(results.content().getFirst().mac()).isEqualTo(new Mac("00:14:22:01:23:45"));
        assertThat(results.content().getFirst().name()).isEqualTo("Sensor_Temperature_1");
        assertThat(results.content().getFirst().registrationDate()).isBefore(LocalDateTime.now());
        assertThat(results.content().getLast().mac()).isEqualTo(new Mac("00:26:85:10:79:52"));
        assertThat(results.content().getLast().name()).isEqualTo("Sensor_Temperature_4");
        assertThat(results.content().getLast().registrationDate()).isBefore(LocalDateTime.now());
        assertThat(results.pageRequest()).isEqualTo(pageRequest);
        assertThat(results.totalElements()).isEqualTo(15);
        assertThat(results.totalPages()).isEqualTo(2);
    }

    @Test
    @DisplayName("find second page of sensors")
    void find_second_page() {
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:14:22:01:23:45', 'Sensor_Temperature_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:16:36:02:46:58', 'Sensor_Humidity_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:18:64:03:59:69', 'Sensor_Pressure_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1A:79:04:12:89', 'Sensor_Temperature_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1C:85:05:25:92', 'Sensor_Humidity_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1E:48:06:33:14', 'Sensor_Pressure_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:20:7B:07:44:27', 'Sensor_Temperature_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:22:4D:08:57:38', 'Sensor_Humidity_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:24:13:09:67:41', 'Sensor_Pressure_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:26:85:10:79:52', 'Sensor_Temperature_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:28:93:11:81:66', 'Sensor_Humidity_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2A:57:12:94:72', 'Sensor_Pressure_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2C:10:13:05:82', 'Sensor_Temperature_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2E:30:14:16:93', 'Sensor_Humidity_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:30:40:15:28:04', 'Sensor_Pressure_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:32:67:16:39:15', 'Sensor_Temperature_6')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:34:80:17:41:26', 'Sensor_Humidity_6')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:36:92:18:52:37', 'Sensor_Pressure_6')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:38:A3:19:63:48', 'Sensor_Temperature_7')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:3A:B4:20:75:59', 'Sensor_Humidity_7')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:3C:C6:21:86:60', 'Sensor_Pressure_7')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:3E:D8:22:98:71', 'Sensor_Temperature_8')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:40:EA:23:10:82', 'Sensor_Humidity_8')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:42:F2:24:21:93', 'Sensor_Pressure_8')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:44:3F:25:32:04', 'Sensor_Temperature_9')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:46:50:26:43:15', 'Sensor_Humidity_9')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:48:61:27:54:26', 'Sensor_Pressure_9')");
        PageRequest pageRequest = new PageRequest(2, 10);
        FindSensorsRepository findSensorsRepository = new FindSensorsRepositoryImpl(jdbcTemplate);

        Page<Sensor> results = findSensorsRepository.findSensors(pageRequest);

        assertThat(results).isNotNull();
        assertThat(results.content().size()).isEqualTo(10);
        assertThat(results.content().getFirst().mac()).isEqualTo(new Mac("00:28:93:11:81:66"));
        assertThat(results.content().getFirst().name()).isEqualTo("Sensor_Humidity_4");
        assertThat(results.content().getFirst().registrationDate()).isBefore(LocalDateTime.now());
        assertThat(results.content().getLast().mac()).isEqualTo(new Mac("00:3A:B4:20:75:59"));
        assertThat(results.content().getLast().name()).isEqualTo("Sensor_Humidity_7");
        assertThat(results.content().getLast().registrationDate()).isBefore(LocalDateTime.now());
        assertThat(results.pageRequest()).isEqualTo(pageRequest);
        assertThat(results.totalElements()).isEqualTo(27);
        assertThat(results.totalPages()).isEqualTo(3);
    }

    @Test
    @DisplayName("find last page of sensors")
    void find_last_page() {
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:14:22:01:23:45', 'Sensor_Temperature_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:16:36:02:46:58', 'Sensor_Humidity_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:18:64:03:59:69', 'Sensor_Pressure_1')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1A:79:04:12:89', 'Sensor_Temperature_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1C:85:05:25:92', 'Sensor_Humidity_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:1E:48:06:33:14', 'Sensor_Pressure_2')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:20:7B:07:44:27', 'Sensor_Temperature_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:22:4D:08:57:38', 'Sensor_Humidity_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:24:13:09:67:41', 'Sensor_Pressure_3')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:26:85:10:79:52', 'Sensor_Temperature_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:28:93:11:81:66', 'Sensor_Humidity_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2A:57:12:94:72', 'Sensor_Pressure_4')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2C:10:13:05:82', 'Sensor_Temperature_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:2E:30:14:16:93', 'Sensor_Humidity_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:30:40:15:28:04', 'Sensor_Pressure_5')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:32:67:16:39:15', 'Sensor_Temperature_6')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:34:80:17:41:26', 'Sensor_Humidity_6')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:36:92:18:52:37', 'Sensor_Pressure_6')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:38:A3:19:63:48', 'Sensor_Temperature_7')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:3A:B4:20:75:59', 'Sensor_Humidity_7')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:3C:C6:21:86:60', 'Sensor_Pressure_7')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:3E:D8:22:98:71', 'Sensor_Temperature_8')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:40:EA:23:10:82', 'Sensor_Humidity_8')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:42:F2:24:21:93', 'Sensor_Pressure_8')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:44:3F:25:32:04', 'Sensor_Temperature_9')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:46:50:26:43:15', 'Sensor_Humidity_9')");
        jdbcTemplate.execute("INSERT INTO SENSAPI.SENSOR (mac, name) VALUES ('00:48:61:27:54:26', 'Sensor_Pressure_9')");
        PageRequest pageRequest = new PageRequest(3, 10);
        FindSensorsRepository findSensorsRepository = new FindSensorsRepositoryImpl(jdbcTemplate);

        Page<Sensor> results = findSensorsRepository.findSensors(pageRequest);

        assertThat(results).isNotNull();
        assertThat(results.content().size()).isEqualTo(7);
        assertThat(results.content().getFirst().mac()).isEqualTo(new Mac("00:3C:C6:21:86:60"));
        assertThat(results.content().getFirst().name()).isEqualTo("Sensor_Pressure_7");
        assertThat(results.content().getFirst().registrationDate()).isBefore(LocalDateTime.now());
        assertThat(results.content().getLast().mac()).isEqualTo(new Mac("00:48:61:27:54:26"));
        assertThat(results.content().getLast().name()).isEqualTo("Sensor_Pressure_9");
        assertThat(results.content().getLast().registrationDate()).isBefore(LocalDateTime.now());
        assertThat(results.pageRequest()).isEqualTo(pageRequest);
        assertThat(results.totalElements()).isEqualTo(27);
        assertThat(results.totalPages()).isEqualTo(3);
    }

}