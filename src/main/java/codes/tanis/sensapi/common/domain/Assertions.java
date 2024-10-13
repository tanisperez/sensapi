package codes.tanis.sensapi.common.domain;

public final class Assertions {

    private Assertions() {
        // Do nothing
    }

    public static void asserts(final Assertion assertion, final String errorMessage) {
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
