package codes.tanis.sensapi.sensor.infrastructure;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.common.domain.PageRequest;
import codes.tanis.sensapi.sensor.domain.Sensor;
import codes.tanis.sensapi.sensor.domain.SensorRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import static codes.tanis.sensapi.common.domain.Todo.TODO;

public class SensorRepositoryImpl implements SensorRepository {

    private final JdbcTemplate jdbcTemplate;

    public SensorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Page<Sensor> findSensors(PageRequest pageRequest) {
        TODO("Not yet implemented");
        return null;
    }

}
