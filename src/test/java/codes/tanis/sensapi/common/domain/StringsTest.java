package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringsTest {

    @Test
    @DisplayName("isEmpty")
    void is_empty() {
        assertThat(Strings.isEmpty(null)).isTrue();
        assertThat(Strings.isEmpty("")).isTrue();
        assertThat(Strings.isEmpty(" ")).isFalse();
        assertThat(Strings.isEmpty("    ")).isFalse();
        assertThat(Strings.isEmpty("Hello")).isFalse();
    }

    @Test
    @DisplayName("isNotEmpty")
    void is_not_empty() {
        assertThat(Strings.isNotEmpty(null)).isFalse();
        assertThat(Strings.isNotEmpty("")).isFalse();
        assertThat(Strings.isNotEmpty(" ")).isTrue();
        assertThat(Strings.isNotEmpty("    ")).isTrue();
        assertThat(Strings.isNotEmpty("Hello")).isTrue();
    }

    @Test
    @DisplayName("isBlank")
    void is_blank() {
        assertThat(Strings.isBlank(null)).isTrue();
        assertThat(Strings.isBlank("")).isTrue();
        assertThat(Strings.isBlank(" ")).isTrue();
        assertThat(Strings.isBlank("    ")).isTrue();
        assertThat(Strings.isBlank("  Hello")).isFalse();
        assertThat(Strings.isBlank("  Hello     ")).isFalse();
    }

    @Test
    @DisplayName("isNotBlank")
    void is_not_blank() {
        assertThat(Strings.isNotBlank(null)).isFalse();
        assertThat(Strings.isNotBlank("")).isFalse();
        assertThat(Strings.isNotBlank(" ")).isFalse();
        assertThat(Strings.isNotBlank("    ")).isFalse();
        assertThat(Strings.isNotBlank("  Hello")).isTrue();
        assertThat(Strings.isNotBlank("  Hello     ")).isTrue();
    }

}