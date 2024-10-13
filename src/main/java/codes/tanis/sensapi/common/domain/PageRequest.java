package codes.tanis.sensapi.common.domain;

import static codes.tanis.sensapi.common.domain.Assertions.asserts;

public record PageRequest (
    int page,
    int size
) {
    private static final int MIN_COUNT = 10;
    private static final int MAX_COUNT = 100;

    public PageRequest {
        asserts(() -> page >= 1, "Page number must be 1 or greater");
        asserts(() -> size >= MIN_COUNT, "The number of records to fetch must be " + MIN_COUNT + " or greater");
        asserts(() -> size <= MAX_COUNT, "The number of records to fetch must not exceed " + MAX_COUNT);
    }
}