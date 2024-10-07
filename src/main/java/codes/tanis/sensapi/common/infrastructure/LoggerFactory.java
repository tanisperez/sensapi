package codes.tanis.sensapi.common.infrastructure;

import codes.tanis.sensapi.common.domain.Logger;

public final class LoggerFactory {

    private LoggerFactory() {
        // Do nothing
    }

    public static Logger getLogger(final Class<?> clazz) {
        final org.slf4j.Logger sl4jLogger = org.slf4j.LoggerFactory.getLogger(clazz);
        return new LogbackLogger(sl4jLogger);
    }

}
