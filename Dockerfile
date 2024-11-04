FROM amazoncorretto:21-alpine3.20

ARG VERSION

COPY /build/libs/npps-${VERSION}-fat.jar /opt/

CMD ["java","-jar","/opt/npps-${VERSION}-fat.jar"]