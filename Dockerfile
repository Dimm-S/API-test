FROM maven:3.6.3-jdk-11

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app

#ENV TRAINER_ID= вставить id тренера и раскомментить строку
#ENV TRAINER_TOKEN= вставить токен тренера и раскомментить строку
#ENV TRAINER_NAME= вставить имя тренера и раскомментить строку. Если имя содержит пробелы, обязательно в двойных кавычках

CMD mvn clean test