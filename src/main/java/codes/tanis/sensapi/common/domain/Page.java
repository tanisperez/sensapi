package codes.tanis.sensapi.common.domain;

import java.util.Collections;
import java.util.List;

public final class Page<T> {
    private final List<T> content;
    private final PageRequest pageRequest;
    private final long totalElements;
    private final long totalPages;

    public Page(final List<T> content, final PageRequest pageRequest, final long totalElements) {
        Assert.notNull(content, "Page content must not be null or empty");
        Assert.notNull(pageRequest, "Page request must not be null");
        Assert.that(() -> totalElements >= 0, "Total elements must not be negative");

        this.content = Collections.unmodifiableList(content);
        this.pageRequest = pageRequest;
        this.totalElements = totalElements;
        this.totalPages = Math.ceilDiv(this.totalElements, this.pageRequest.size());
    }

    public List<T> getContent() {
        return content;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }
}
