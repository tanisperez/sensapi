package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AssertTest {

    @Test
    @DisplayName("Should Assert.that")
    void should_assert_that() {
        final String input = null;

        assertThatThrownBy(() ->  Assert.that(() -> input != null, "Input should not be null"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input should not be null");

        final String text = "Some text";

        Assert.that(() -> text != null, "Input should not be null");
    }

    @Test
    @DisplayName("Should Assert.notNull")
    void should_assert_not_null() {
        final String text = null;
        assertThatThrownBy(() ->  Assert.notNull(text, "Input should not be null"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input should not be null");

        Assert.notNull("Something", "Input should not be null");
    }

}