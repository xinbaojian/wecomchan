compile: clean
	mvn compile -e -U

clean:
	mvn clean

package:
	mvn clean package -e -U -DskipTests

build: package
	mvn docker:build docker:push