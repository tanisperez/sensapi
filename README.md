# SensAPI

**SensAPI** is a REST API designed for storing and retrieving sensor data.

## Requirements

* Java 21 and Maven are required to build **SensAPI**. It's recommended to use [SDKMAN!](https://sdkman.io/) for easy installation and configuration of JDK 21.
* To generate a native image, a **GraalVM** JDK is required, which can also be installed via SDKMAN!.
* The project utilizes **Maven Wrapper** for build consistency. The Maven version is defined in `.mvn/wrapper/maven-wrapper.properties`.
* Ensure the `JAVA_HOME` environment variable is set. **SDKMAN!** will handle this automatically.

A `Makefile` is provided for convenience, offering shortcuts for common actions.

## Build

You can build **SensAPI** either as a standard **JAR** file (for the Java Virtual Machine) or as a native executable.

### Build JAR

To generate the **JAR** file, run:

```bash
make build 
```

This will create the file `target/sensapi.jar`.

### Built Native Image

To build a native executable, run:

```bash
make build-native
```

This process may take a few minutes depending on your machine and will generate the file `target/sensapi`.

## Test

## Execution