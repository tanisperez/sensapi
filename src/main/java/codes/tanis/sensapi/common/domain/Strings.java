package codes.tanis.sensapi.common.domain;

public final class Strings {

    private Strings() {
        // Do nothing
    }

    public static boolean isNullOrEmpty(final String text) {
        return text == null || text.isEmpty();
    }

    public static boolean isNullOrBlank(final String text) {
        return text == null || text.isBlank();
    }

}
