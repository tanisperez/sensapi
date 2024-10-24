package codes.tanis.sensapi.common.domain;

public record PageRequest (
    int page,
    int size
) {
    private static final int MIN_COUNT = 10;
    private static final int MAX_COUNT = 100;

    public PageRequest {
        Assert.that(() -> page >= 1, "Page number must be 1 or greater");
        Assert.that(() -> size >= MIN_COUNT, "The number of records to fetch must be " + MIN_COUNT + " or greater");
        Assert.that(() -> size <= MAX_COUNT, "The number of records to fetch must not exceed " + MAX_COUNT);
    }
}