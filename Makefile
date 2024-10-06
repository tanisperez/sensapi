MAVEN=./mvnw

run:
	java -jar target/sensapi.jar

run-native:
	./target/sensapi

build:
	$(MAVEN) package -DskipTests

build-native:
	$(MAVEN) native:compile -Pnative -DskipTests

test:
	$(MAVEN) test

test-native:
	$(MAVEN) test -PnativeTest

clean:
	$(MAVEN) clean