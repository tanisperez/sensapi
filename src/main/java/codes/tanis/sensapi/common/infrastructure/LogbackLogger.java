package codes.tanis.sensapi.common.infrastructure;

import codes.tanis.sensapi.common.domain.Logger;

final class LogbackLogger implements Logger {

    private final org.slf4j.Logger logger;

    LogbackLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public void info(String message) {
        this.logger.info(message);
    }
}
