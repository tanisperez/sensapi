package codes.tanis.sensapi.sensor.domain;

import codes.tanis.sensapi.common.domain.Strings;

import java.time.LocalDateTime;

import static codes.tanis.sensapi.common.domain.Assertions.asserts;

public record Sensor(
    Mac mac,
    String name,
    LocalDateTime registrationDate
) {
    private static final int MAX_NAME_LENGTH = 30;

    public Sensor {
        asserts(() -> mac != null, "The MAC address must not be null");
        asserts(() -> Strings.isNotBlank(name), "The sensor name must not be null or blank");
        asserts(() -> name.length() <= MAX_NAME_LENGTH, "Sensor name must be shorter than " + MAX_NAME_LENGTH + " characters");
        asserts(() -> registrationDate != null, "The registration date must not be null");
    }
}
