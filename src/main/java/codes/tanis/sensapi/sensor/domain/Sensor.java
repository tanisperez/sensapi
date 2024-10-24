package codes.tanis.sensapi.sensor.domain;

import codes.tanis.sensapi.common.domain.Assert;
import codes.tanis.sensapi.common.domain.Strings;

import java.time.LocalDateTime;

public record Sensor(
    Mac mac,
    String name,
    LocalDateTime registrationDate
) {
    private static final int MAX_NAME_LENGTH = 30;

    public Sensor {
        Assert.that(() -> mac != null, "The MAC address must not be null");
        Assert.that(() -> Strings.isNotBlank(name), "The sensor name must not be null or blank");
        Assert.that(() -> name.length() <= MAX_NAME_LENGTH, "Sensor name must be shorter than " + MAX_NAME_LENGTH + " characters");
        Assert.that(() -> registrationDate != null, "The registration date must not be null");
    }
}
