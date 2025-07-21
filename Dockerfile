FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN apt-get update && apt-get install -y curl unzip && \
    curl -L https://github.com/sbt/sbt/releases/download/v1.9.9/sbt-1.9.9.zip -o sbt.zip && \
    unzip sbt.zip -d /sbt && \
    mv /sbt/sbt/bin/* /usr/bin/ && \
    sbt compile

EXPOSE 9000

CMD ["sbt", "-Dconfig.file=conf/docker.conf", "run"]
