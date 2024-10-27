package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PageTest {

    @Test
    @DisplayName("Should create the first and single page")
    public void should_create_first_and_single_page() {
        final List<String> content = List.of("a", "b", "c", "d" ,"e");
        final PageRequest pageRequest = new PageRequest(1, 10);

        final Page<String> page = new Page<>(content, pageRequest, 5);

        assertThat(page.content()).isEqualTo(content);
        assertThat(page.content()).isNotSameAs(content);
        assertThat(page.pageRequest()).isSameAs(pageRequest);
        assertThat(page.totalElements()).isEqualTo(5);
        assertThat(page.totalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should create the first page")
    public void should_create_the_first_page() {
        final List<Integer> content = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final PageRequest pageRequest = new PageRequest(1, 10);

        final Page<Integer> page = new Page<>(content, pageRequest, 20);

        assertThat(page.content()).isEqualTo(content);
        assertThat(page.content()).isNotSameAs(content);
        assertThat(page.pageRequest()).isSameAs(pageRequest);
        assertThat(page.totalElements()).isEqualTo(20);
        assertThat(page.totalPages()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should create the second page")
    public void should_create_the_second_page() {
        final List<Integer> content = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final PageRequest pageRequest = new PageRequest(2, 10);

        final Page<Integer> page = new Page<>(content, pageRequest, 43);

        assertThat(page.content()).isEqualTo(content);
        assertThat(page.content()).isNotSameAs(content);
        assertThat(page.pageRequest()).isSameAs(pageRequest);
        assertThat(page.totalElements()).isEqualTo(43);
        assertThat(page.totalPages()).isEqualTo(5);
    }

    @Test
    @DisplayName("Should create last page")
    public void should_create_last_page() {
        final List<String> content = List.of("a", "b", "c", "d" ,"e");
        final PageRequest pageRequest = new PageRequest(3, 25);

        final Page<String> page = new Page<>(content, pageRequest, (25 * 2) + 5);

        assertThat(page.content()).isEqualTo(content);
        assertThat(page.content()).isNotSameAs(content);
        assertThat(page.pageRequest()).isSameAs(pageRequest);
        assertThat(page.totalElements()).isEqualTo(55);
        assertThat(page.totalPages()).isEqualTo(3);
    }

    @Test
    @DisplayName("Should throw an IllegalArgumentException")
    public void should_throw_exception() {
        assertThatThrownBy(() -> new Page<>(null, null, 0L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Page content must not be null or empty");

        assertThatThrownBy(() -> new Page<>(List.of(1, 2, 3, 4), null, 0L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Page request must not be null");

        assertThatThrownBy(() -> new Page<>(List.of(1, 2, 3, 4), new PageRequest(1, 10), -1L))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Total elements must not be negative");
    }

}