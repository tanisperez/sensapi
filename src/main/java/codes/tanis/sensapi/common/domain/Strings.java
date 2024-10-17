package codes.tanis.sensapi.common.domain;

public final class Strings {

    private Strings() {
        // Do nothing
    }

    public static boolean isEmpty(final String text) {
        return text == null || text.isEmpty();
    }

    public static boolean isNotEmpty(final String text) {
        return !isEmpty(text);
    }

    public static boolean isBlank(final String text) {
        return text == null || text.isBlank();
    }

    public static boolean isNotBlank(final String text) {
        return !isBlank(text);
    }

}
