package codes.tanis.sensapi.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ListsTest {

    @Test
    @DisplayName("isNullOrEmpty")
    public void is_null_or_empty() {
        assertThat(Lists.isNullOrEmpty(null)).isTrue();
        assertThat(Lists.isNullOrEmpty(List.of())).isTrue();
        assertThat(Lists.isNullOrEmpty(new ArrayList<String>())).isTrue();
        assertThat(Lists.isNullOrEmpty(List.of(1, 2, 3))).isFalse();
        assertThat(Lists.isNullOrEmpty(Arrays.asList("a", "b", "c"))).isFalse();
    }

}