FROM amazoncorretto:21-alpine3.20

ARG VERSION

COPY /build/libs/npps-${VERSION}-fat.jar /opt/npp-fat.jar

ENTRYPOINT ["java","-jar","/opt/npp-fat.jar"]