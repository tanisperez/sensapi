package codes.tanis.sensapi.common.domain;

public final class Asserts {

    private Asserts() {
        // Do nothing
    }

    public static void that(final Assertion assertion, final String errorMessage) {
        if (assertion.isFalse()) {
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
