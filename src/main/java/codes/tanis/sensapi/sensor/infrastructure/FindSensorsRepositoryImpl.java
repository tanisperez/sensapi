package codes.tanis.sensapi.sensor.infrastructure;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.common.domain.PageRequest;
import codes.tanis.sensapi.sensor.domain.Mac;
import codes.tanis.sensapi.sensor.domain.Sensor;
import codes.tanis.sensapi.sensor.domain.FindSensorsRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class FindSensorsRepositoryImpl implements FindSensorsRepository {

    private final JdbcTemplate jdbcTemplate;

    public FindSensorsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Sensor> findSensors(PageRequest pageRequest) {
        final long tableOffset = (long) pageRequest.size() * (pageRequest.page() - 1);

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
                    ROW_NUMBER() OVER (ORDER BY REGISTRATION_DATE ASC) AS ROW_NUMBER,
                    COUNT(*) OVER () AS TOTAL_ROWS
                FROM SENSAPI.SENSOR
                ORDER BY REGISTRATION_DATE ASC
            ) t
            WHERE
                t.ROW_NUMBER > ?
            LIMIT ?
        """, FindSensorsRepositoryImpl::mapRow, tableOffset, pageRequest.size());

        List<Sensor> sensors = sensorEntities.stream().map(SensorEntity::toSensor).toList();
        long totalElements = getTotalElements(sensorEntities);

        return new Page<>(sensors, pageRequest, totalElements);
    }


    private static SensorEntity mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        String mac = resultSet.getString("MAC");
        String name = resultSet.getString("NAME");
        Timestamp registrationDate = resultSet.getTimestamp("REGISTRATION_DATE");
        long totalRows = resultSet.getLong("TOTAL_ROWS");
        return new SensorEntity(mac, name, registrationDate.toLocalDateTime(), totalRows);
    }

    private long getTotalElements(List<SensorEntity> sensorEntities) {
        if (sensorEntities.isEmpty()) {
            return 0;
        }
        return sensorEntities.getFirst().totalRows();
    }

    private record SensorEntity(
        String mac,
        String name,
        LocalDateTime registrationDate,
        long totalRows
    ) {
        public Sensor toSensor() {
            return new Sensor(
                new Mac(this.mac),
                this.name,
                this.registrationDate
            );
        }
    }

}
