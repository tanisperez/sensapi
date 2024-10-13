package codes.tanis.sensapi.common.domain;

import static codes.tanis.sensapi.common.domain.Assertions.asserts;

public record PageRequest (
    int page,
    int size
) {
    private static final int MIN_COUNT = 10;
    private static final int MAX_COUNT = 100;

    public PageRequest {
        asserts(() -> page >= 1, "Page number could no be lower than 1");
        asserts(() -> size >= MIN_COUNT, "The number of records to fetch could not be lower than " + MIN_COUNT);
        asserts(() -> size <= MAX_COUNT, "The number of records to fetch could not be higher than " + MAX_COUNT);
    }
}