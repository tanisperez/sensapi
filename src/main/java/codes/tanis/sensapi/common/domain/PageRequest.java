package codes.tanis.sensapi.common.domain;

public record PageRequest (
    int page,
    int size
) {
    private static final int MIN_COUNT = 10;
    private static final int MAX_COUNT = 100;

    public PageRequest {
        if (page < 1) {
            throw new IllegalArgumentException("Page number could no be lower than 1");
        }
        if (size < MIN_COUNT) {
            throw new IllegalArgumentException("The number of records to fetch could not be lower than " + MIN_COUNT);
        }
        if (size > MAX_COUNT) {
            throw new IllegalArgumentException("The number of records to fetch could not be higher than " + MAX_COUNT);
        }
    }
}