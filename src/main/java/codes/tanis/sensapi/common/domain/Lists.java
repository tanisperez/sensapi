package codes.tanis.sensapi.common.domain;

import java.util.List;

public final class Lists {

    private Lists() {
        // Do nothing
    }

    public static <T> boolean isNullOrEmpty(final List<T> list) {
        return list == null || list.isEmpty();
    }

}
