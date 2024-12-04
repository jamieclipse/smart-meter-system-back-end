# smart-meter-system-back-end
[![Java CI with Maven](https://github.com/jamieclipse/smart-meter-system-back-end/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/jamieclipse/smart-meter-system-back-end/actions/workflows/maven.yml)

To install and run the application, follow these steps:

1. Make sure you have Java 21 installed on your machine. [GraalVM](https://www.graalvm.org/latest/getting-started/) is recommended and was used to build and test this application, but any JDK should work.

2. [Make sure you have RabbitMQ installed on your machine.](https://www.rabbitmq.com/docs/download) Confirm that RabbitMQ is up and accessible via localhost before proceeding.

3. Clone the repository by running the following command in your terminal:
    ```
    git clone https://github.com/jamieclipse/smart-meter-system-back-end.git
    ```

4. Build the application by running the following command:
    ```
    ./mvnw clean install
    ```

5. Start the application by running the following command:
    ```
    java -jar target/smart-meter-system-back-end-1.0.0.jar
    ```
