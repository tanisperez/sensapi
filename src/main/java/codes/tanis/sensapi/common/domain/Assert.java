package codes.tanis.sensapi.common.domain;

public final class Assert {

    private Assert() {
        // Do nothing
    }

    public static void that(final Assertion assertion, final String errorMessage) {
        if (assertion.isFalse()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void notNull(final Object object, final String errorMessage) {
        if (object == null) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    @FunctionalInterface
    public interface Assertion {

        boolean isTrue();

        default boolean isFalse() {
            return !isTrue();
        }

    }

}
