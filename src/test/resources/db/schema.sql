CREATE SCHEMA IF NOT EXISTS sensapi;

-- sensor
CREATE TABLE sensapi.sensor (
	mac CHAR(17) PRIMARY KEY,
	name VARCHAR(30) NOT NULL,
	registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);