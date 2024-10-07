package codes.tanis.sensapi.common.infrastructure;

import codes.tanis.sensapi.common.domain.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LogbackLoggerTest {

    @Test
    @DisplayName("Log as info level")
    public void log_as_info_level() {
        org.slf4j.Logger sl4jLoggerMock = Mockito.mock();
        final Logger logger = new LogbackLogger(sl4jLoggerMock);

        logger.info("This is a log test");

        Mockito.verify(sl4jLoggerMock).info("This is a log test");
    }

}
