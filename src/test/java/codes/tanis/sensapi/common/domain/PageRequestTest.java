package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PageRequestTest {

    @Test
    @DisplayName("Should create a PageRequest")
    public void should_create_a_page_request() {
        final PageRequest pageRequest = new PageRequest(3, 25);

        assertThat(pageRequest.page()).isEqualTo(3);
        assertThat(pageRequest.size()).isEqualTo(25);
    }

    @ParameterizedTest(name = "page = {0}, size = {1}, error = {2}")
    @DisplayName("Should throw an IllegalArgumentException")
    @CsvSource({
        "0,10,Page number must be 1 or greater",
        "-5,10,Page number must be 1 or greater",
        "3,4,The number of records to fetch must be 10 or greater",
        "3,150,The number of records to fetch must not exceed 100",
    })
    public void should_throw_exception(int page, int size, String expectedErrorMessage) {
        assertThatThrownBy(() -> new PageRequest(page, size))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedErrorMessage);
    }

}