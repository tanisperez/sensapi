package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PageRequestTest {

    @Test
    @DisplayName("Should create a PageRequest")
    public void should_create_a_page_request() {
        final PageRequest pageRequest = new PageRequest(3, 25);

        assertThat(pageRequest.page()).isEqualTo(3);
        assertThat(pageRequest.count()).isEqualTo(25);
    }

    @ParameterizedTest(name = "page = {0}, count = {1}, error = {2}")
    @DisplayName("Should throw an IllegalArgumentException")
    @CsvSource({
        "0,10,Page number could no be lower than 1",
        "-5,10,Page number could no be lower than 1",
        "3,4,The number of records to fetch could not be lower than 10",
        "3,150,The number of records to fetch could not be higher than 100",
    })
    public void should_throw_exception(int page, int count, String expectedErrorMessage) {
        assertThatThrownBy(() -> new PageRequest(page, count))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(expectedErrorMessage);
    }

}