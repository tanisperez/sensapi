package codes.tanis.sensapi.common.domain;

import java.util.Collections;
import java.util.List;

public final class Page<T> {
    private final List<T> content;
    private final PageRequest pageRequest;
    private final long totalElements;
    private final long totalPages;

    public Page(final List<T> content, final PageRequest pageRequest, final long totalElements) {
        Asserts.that(() -> content != null, "Page content must not be null or empty");
        Asserts.that(() -> pageRequest != null, "Page request must not be null");
        Asserts.that(() -> totalElements > 0, "Total elements must be greater than 0");

        this.content = Collections.unmodifiableList(content);
        this.pageRequest = pageRequest;
        this.totalElements = totalElements;
        this.totalPages = Math.ceilDiv(this.totalElements, this.pageRequest.size());
    }

    public List<T> content() {
        return content;
    }

    public PageRequest pageRequest() {
        return pageRequest;
    }

    public long totalElements() {
        return totalElements;
    }

    public long totalPages() {
        return totalPages;
    }
}
