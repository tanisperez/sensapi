package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class StringsTest {

    @Test
    @DisplayName("isEmpty")
    public void is_empty() {
        assertThat(Strings.isEmpty(null)).isTrue();
        assertThat(Strings.isEmpty("")).isTrue();
        assertThat(Strings.isEmpty(" ")).isFalse();
        assertThat(Strings.isEmpty("    ")).isFalse();
        assertThat(Strings.isEmpty("Hello")).isFalse();
    }

    @Test
    @DisplayName("isBlank")
    public void is_blank() {
        assertThat(Strings.isBlank(null)).isTrue();
        assertThat(Strings.isBlank("")).isTrue();
        assertThat(Strings.isBlank(" ")).isTrue();
        assertThat(Strings.isBlank("    ")).isTrue();
        assertThat(Strings.isBlank("  Hello")).isFalse();
        assertThat(Strings.isBlank("  Hello     ")).isFalse();
    }

}