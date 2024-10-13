package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AssertionsTest {

    @Test
    @DisplayName("Should fail the assertion")
    public void should_assert_false() {
        final String input = null;

        assertThatThrownBy(() ->  Assertions.asserts(() -> input != null, "Input should not be null"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Input should not be null");
    }

    @Test
    @DisplayName("Should success the assertion")
    public void should_assert_true() {
        final String text = "Some text";

        Assertions.asserts(() -> text != null, "Input should not be null");
    }

}