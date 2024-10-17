package codes.tanis.sensapi.common.domain;

public final class Todo {

    private Todo() {
        // Do nothing
    }

    public static void TODO(final String reason) {
        throw new NotImplementedException(reason);
    }

    public static final class NotImplementedException extends RuntimeException {
        public NotImplementedException(String message) {
            super(message);
        }
    }

}
