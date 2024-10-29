package codes.tanis.sensapi.sensor.domain;

import codes.tanis.sensapi.common.domain.Page;
import codes.tanis.sensapi.common.domain.PageRequest;

public interface FindSensorsRepository {

    Page<Sensor> findSensors(final PageRequest pageRequest);

}
