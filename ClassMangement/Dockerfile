FROM   openjdk
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY  ${JAR_FILE} /app.jar
ENTRYPOINT [ "java","-jar","/app.jar"]
#docker build -t class-management:01 .
#docker run -p 9292:9191 class-management:01