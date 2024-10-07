package codes.tanis.sensapi.common.infrastructure;

import codes.tanis.sensapi.common.domain.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggerFactoryTest {

    @Test
    @DisplayName("Return a Logger instance")
    public void return_a_logger_instance() {
        final Logger logger = LoggerFactory.getLogger(LoggerFactoryTest.class);

        assertThat(logger).isInstanceOf(LogbackLogger.class);
    }

}
