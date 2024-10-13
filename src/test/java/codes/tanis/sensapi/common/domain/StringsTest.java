package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringsTest {

    @Test
    @DisplayName("isNullOrEmpty")
    public void is_null_or_empty() {
        assertThat(Strings.isNullOrEmpty(null)).isTrue();
        assertThat(Strings.isNullOrEmpty("")).isTrue();
        assertThat(Strings.isNullOrEmpty(" ")).isFalse();
        assertThat(Strings.isNullOrEmpty("    ")).isFalse();
        assertThat(Strings.isNullOrEmpty("Hello")).isFalse();
    }

    @Test
    @DisplayName("isNullOrBlank")
    public void is_null_or_blank() {
        assertThat(Strings.isNullOrBlank(null)).isTrue();
        assertThat(Strings.isNullOrBlank("")).isTrue();
        assertThat(Strings.isNullOrBlank(" ")).isTrue();
        assertThat(Strings.isNullOrBlank("    ")).isTrue();
        assertThat(Strings.isNullOrBlank("  Hello")).isFalse();
        assertThat(Strings.isNullOrBlank("  Hello     ")).isFalse();
    }

}