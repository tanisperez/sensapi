package codes.tanis.sensapi.sensor.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MacTest {

    @Test
    @DisplayName("is valid MAC address")
    public void is_valid_mac_address() {
        final Mac mac = new Mac("01:23:45:67:89:AB");

        assertThat(mac.address()).isEqualTo("01:23:45:67:89:AB");
    }

    @Test
    @DisplayName("should convert to uppercase a valid MAC address")
    public void should_convert_to_uppercase_a_valid_mac_address() {
        final Mac mac = new Mac("01:ab:0c:67:89:ab");

        assertThat(mac.address()).isEqualTo("01:AB:0C:67:89:AB");
    }

    @Test
    @DisplayName("is null MAC address")
    public void is_null_mac_address() {
        assertThatThrownBy(() -> new Mac(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("The MAC address must not be null");
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource({
        "01:23:45:67:89:GZ",
        "01:23:45:67:89",
        "01:23:45:67:89:AB:CD",
        "01-23-45-67-89-AB",
        "0123.4567.89AB",
        "01:23:45:67:89:AB:",
        ":01:23:45:67:89:AB",
        "01:23:45:67:89:AB:GG",
        "01:23:45:67:89:XYZ",
        "01 23 45 67 89 AB",
        "01::23:45:67:89:AB",
        "01:23:45:67:89:AB:",
        "01:23:45:67:89",
        "01-23:45-67:89:AB",
        "GG:HH:II:JJ:KK:LL",
        "01:23:45:67:89:AB:0",
        "01:23:45:67:8:AB",
        "01:23:45:67:89:AB:EF",
        "XX:XX:XX:XX:XX:XX",
        "01:23:45:67:89:ZZ"
    })
    public void is_invalid_mac_address(final String macAddress) {
        assertThatThrownBy(() -> new Mac(macAddress))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Invalid MAC address. Use the format XX:XX:XX:XX:XX:XX, with hexadecimal pairs (0-9, A-F)");
    }

}