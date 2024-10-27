package codes.tanis.sensapi.sensor.infrastructure;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.common.domain.PageRequest;
import codes.tanis.sensapi.sensor.domain.Mac;
import codes.tanis.sensapi.sensor.domain.Sensor;
import codes.tanis.sensapi.sensor.domain.SensorRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class SensorRepositoryImpl implements SensorRepository {

    private final JdbcTemplate jdbcTemplate;

    public SensorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Sensor> findSensors(PageRequest pageRequest) {
        final long tableOffset = (long) pageRequest.size() * pageRequest.page();

        List<SensorEntity> sensorEntities = jdbcTemplate.query("""
            SELECT
                t.MAC,
                t.NAME,
                t.REGISTRATION_DATE,
                t.ROW_NUMBER,
                t.TOTAL_ROWS
            FROM (
                SELECT 
                    MAC, 
                    NAME, 
                    REGISTRATION_DATE,
                    ROW_NUMBER() OVER (ORDER BY REGISTRATION_DATE) AS ROW_NUMBER,
                    COUNT(*) OVER () AS TOTAL_ROWS
                FROM SENSAPI.SENSOR
            ) t
            WHERE
                t.ROW_NUMBER > ?
            LIMIT ?
        """, (resultSet, rowNum) -> {
            String mac = resultSet.getString("MAC");
            String name = resultSet.getString("NAME");
            Timestamp registrationDate = resultSet.getTimestamp("REGISTRATION_DATE");
            long rowNumber = resultSet.getLong("ROW_NUMBER");
            long totalRows = resultSet.getLong("TOTAL_ROWS");
            return new SensorEntity(mac, name, registrationDate.toLocalDateTime());
        }, tableOffset, pageRequest.size());

        List<Sensor> sensors = sensorEntities.stream().map(SensorEntity::toSensor).toList();

        return new Page<>(sensors, pageRequest, 0);
    }

    private record SensorEntity(
        String mac,
        String name,
        LocalDateTime registrationDate
    ) {
        public Sensor toSensor() {
            return new Sensor(new Mac(this.mac), this.name, this.registrationDate);
        }
    }

}
