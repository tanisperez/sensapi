package codes.tanis.sensapi.sensor.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SensorTest {

    @Test
    @DisplayName("is valid Sensor")
    public void is_valid_sensor() {
        final Mac mac = new Mac("01:23:45:67:89:AB");
        final Sensor sensor = new Sensor(mac, "ESP32-LIGHTS", LocalDateTime.parse("2024-10-16T23:12:14"));

        assertThat(sensor.mac().address()).isEqualTo("01:23:45:67:89:AB");
        assertThat(sensor.name()).isEqualTo("ESP32-LIGHTS");
        assertThat(sensor.registrationDate()).isEqualTo(LocalDateTime.parse("2024-10-16T23:12:14"));
    }

    @Test
    @DisplayName("is null MAC address")
    public void is_null_mac_address() {
        assertThatThrownBy(() -> new Sensor(null, "ESP32-LIGHTS", null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The MAC address must not be null");
    }

    @Test
    @DisplayName("is invalid sensor name")
    public void is_invalid_sensor_name() {
        final Mac mac = new Mac("01:23:45:67:89:AB");
        assertThatThrownBy(() -> new Sensor(mac, null, null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The sensor name must not be null or blank");

        assertThatThrownBy(() -> new Sensor(mac, "       ", null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The sensor name must not be null or blank");

        assertThatThrownBy(() -> new Sensor(mac, "abkjqklwdj klqwjdkljq wkdlj qklwjdklqj wkld qw", null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Sensor name must be shorter than 30 characters");
    }

    @Test
    @DisplayName("is null registration date")
    public void is_null_registration_date() {
        final Mac mac = new Mac("01:23:45:67:89:AB");
        assertThatThrownBy(() -> new Sensor(mac, "ESP32-LIGHTS", null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The registration date must not be null");
    }
}