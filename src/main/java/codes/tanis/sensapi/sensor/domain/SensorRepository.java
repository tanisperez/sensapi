package codes.tanis.sensapi.sensor.domain;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.common.domain.PageRequest;

public interface SensorRepository {

    Page<Sensor> findSensors(final PageRequest pageRequest);

}
