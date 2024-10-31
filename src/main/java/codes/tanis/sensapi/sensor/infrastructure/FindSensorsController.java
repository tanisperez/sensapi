package codes.tanis.sensapi.sensor.infrastructure;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.common.domain.PageRequest;
import codes.tanis.sensapi.sensor.domain.FindSensorsRepository;
import codes.tanis.sensapi.sensor.domain.Sensor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FindSensorsController {

    private final FindSensorsRepository findSensorsRepository;

    public FindSensorsController(FindSensorsRepository findSensorsRepository) {
        this.findSensorsRepository = findSensorsRepository;
    }

    @GetMapping("/sensors")
    public Page<SensorDTO> findSensors() {
        PageRequest pageRequest = new PageRequest(1, 10);
        Page<Sensor> sensors = findSensorsRepository.findSensors(pageRequest);
        List<SensorDTO> sensorDTOS = sensors.getContent().stream().map(SensorDTO::from).toList();
        return new Page<>(sensorDTOS, pageRequest, sensors.getTotalElements());
    }

    public record SensorDTO (
        String mac,
        String name,
        LocalDateTime registrationDate
    ) {
        public static SensorDTO from(final Sensor sensor) {
            return new SensorDTO(sensor.mac().address(), sensor.name(), sensor.registrationDate());
        }
    }

}
