package codes.tanis.sensapi.sensor.infrastructure;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.sensor.infrastructure.FindSensorsController.SensorDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@ActiveProfiles("integration")
@Sql(scripts = "/db/schema.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindSensorsIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Should not return any sensors")
    public void should_not_return_any_sensors() {
        ResponseEntity<Page<SensorDTO>> response = restTemplate.exchange(
            "http://localhost:" + port + "/api/v1/sensors",
            HttpMethod.GET,
            HttpEntity.EMPTY,
            new ParameterizedTypeReference<Page<SensorDTO>>() {}
        );

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        Page<SensorDTO> sensors = response.getBody();
        assertThat(sensors).isNotNull();
        assertThat(sensors.getContent()).isNotNull();
        assertThat(sensors.getContent().size()).isEqualTo(0);
        assertThat(sensors.getTotalElements()).isEqualTo(0);
        assertThat(sensors.getTotalPages()).isEqualTo(0);
    }

}
