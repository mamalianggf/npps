FROM amazoncorretto:21-alpine3.20

COPY /build/libs/npps-1.0.0-SNAPSHOT-fat.jar /opt/npps-1.0.0-SNAPSHOT-fat.jar

ENTRYPOINT ["java","-jar","/opt/npps-1.0.0-SNAPSHOT-fat.jar"]