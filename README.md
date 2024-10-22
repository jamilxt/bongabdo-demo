# Bongabdo Demo - Spring Boot (Kotlin)
This is a demo of [Bongabdo - A Bengali Calendar Converter Library](https://github.com/hasancse91/bongabdo) using Spring Boot (Kotlin)

## How to Build & Run
1. Install Java 17 (LTS) of any distribution. You can try Amazon Corretto 17. You can find the installation instructions here: https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html
2. Open this project with your preferred IDE. We prefer IntelliJ IDEA Ultimate Edition. Other IDEs like VS Code or Spring Tools Suite (STS) will do.
3. To build the project execute the following command:
    ```
    ./gradlew clean build
    ```
   Executing that command will generate a jar under "build/libs" folder which you can run using the following command:
    ```
    java -jar erp-0.0.1-SNAPSHOT.jar
    ```
   or, You can run the app directly by following the command:
    ```
    ./gradlew bootRun
    ```
   or, you can run via your preferred IDE's RUN/PLAY button.
4. After running the application, it will be up & running at http://localhost:8080