package codes.tanis.sensapi.sensor.domain;

import java.util.regex.Pattern;

import static codes.tanis.sensapi.common.domain.Assertions.asserts;

public final class Mac {

    private static final String MAC_ADDRESS_REGEX = "^([0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$";
    private static final Pattern MAC_ADDRESS_PATTERN = Pattern.compile(MAC_ADDRESS_REGEX);

    private final String address;

    public Mac(final String address) {
        asserts(() -> address != null, "The MAC address must not be null");
        asserts(() -> MAC_ADDRESS_PATTERN.matcher(address).matches(), "Invalid MAC address. Use the format XX:XX:XX:XX:XX:XX, with hexadecimal pairs (0-9, A-F)");
        this.address = address.toUpperCase();
    }

    public String address() {
        return address;
    }
}
