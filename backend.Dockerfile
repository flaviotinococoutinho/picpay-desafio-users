#CRIANDO O PACOTE
FROM maven:3.8.1-openjdk-11-slim AS packager
#Maven settings caso tenha usuário ou use repositório não público
#COPY settings.xml /root/.m2/
WORKDIR /build

COPY /picpay-teste-backend /build

RUN mvn -f /build/pom.xml clean -U package

RUN mvn package -Dmaven.test.skip=true && \
    mv /build/target/*.jar /build/picpay_teste_backend.jar

FROM openjdk:11-slim

COPY --from=packager /build/picpay_teste_backend.jar /var/picpay_desafio/picpay_teste_backend.jar
COPY --from=packager /build/docker-entrypoint.sh /docker-entrypoint.sh

RUN chmod +x /docker-entrypoint.sh

EXPOSE 8000

ENTRYPOINT [ "bash", "/docker-entrypoint.sh" ]