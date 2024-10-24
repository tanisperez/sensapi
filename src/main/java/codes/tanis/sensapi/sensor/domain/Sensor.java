package codes.tanis.sensapi.sensor.domain;

import codes.tanis.sensapi.common.domain.Asserts;
import codes.tanis.sensapi.common.domain.Strings;

import java.time.LocalDateTime;

public record Sensor(
    Mac mac,
    String name,
    LocalDateTime registrationDate
) {
    private static final int MAX_NAME_LENGTH = 30;

    public Sensor {
        Asserts.that(() -> mac != null, "The MAC address must not be null");
        Asserts.that(() -> Strings.isNotBlank(name), "The sensor name must not be null or blank");
        Asserts.that(() -> name.length() <= MAX_NAME_LENGTH, "Sensor name must be shorter than " + MAX_NAME_LENGTH + " characters");
        Asserts.that(() -> registrationDate != null, "The registration date must not be null");
    }
}
