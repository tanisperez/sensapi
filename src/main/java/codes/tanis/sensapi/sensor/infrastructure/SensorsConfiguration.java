package codes.tanis.sensapi.sensor.infrastructure;

import codes.tanis.sensapi.sensor.domain.FindSensorsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SensorsConfiguration {

    @Bean
    public FindSensorsRepository findSensorsRepository(final JdbcTemplate jdbcTemplate) {
        return new FindSensorsRepositoryImpl(jdbcTemplate);
    }

}
