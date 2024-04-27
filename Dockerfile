FROM ubuntu:latest

RUN apt-get update
RUN apt-get install -y dos2unix nano less file sudo wget htop curl
RUN apt-get install -y mysql-server
RUN apt-get install -y openjdk-17-jdk openjdk-17-jre-headless openjdk-17-source

ARG DEBIAN_FRONTEND=noninteractive
ENV TZ=Europe/Paris
RUN apt-get install -y r-base

RUN Rscript -e 'install.packages(c("ggplot2", "plyr", "ggrepel", "data.table", "arules", "purrr", "reshape2"), repos = "http://cran.rstudio.com/")'

#RUN apt-get install wget curl nano software-properties-common dirmngr apt-transport-https  \
#    gnupg gnupg2 ca-certificates lsb-release ubuntu-keyring unzip -y
#RUN curl -fsSL https://debian.neo4j.com/neotechnology.gpg.key | sudo gpg --dearmor -o /usr/share/keyrings/neo4j.gpg

#RUN echo "deb [signed-by=/usr/share/keyrings/neo4j.gpg] https://debian.neo4j.com stable latest" | sudo tee -a /etc/apt/sources.list.d/neo4j.list
#RUN apt-get update
#RUN apt-get install neo4j -y

#RUN wget -P /var/lib/neo4j/plugins https://github.com/neo4j-contrib/neo4j-apoc-procedures/releases/download/5.16.0/apoc-5.16.0-extended.jar

#RUN touch /etc/neo4j/apoc.conf
#RUN printf "\napoc.procedures.register=all" >> /etc/neo4j/apoc && \
#    printf "\napoc.import.file.enabled=true" >> /etc/neo4j/apoc.conf && \
#    printf "\napoc.export.file.enabled=true" >> /etc/neo4j/apoc.conf

#ENV NEO4J_dbms_connector_https_advertised__address="localhost:7473"
#ENV NEO4J_dbms_connector_http_advertised__address="localhost:7474"
#ENV NEO4J_dbms_connector_bolt_advertised__address="localhost:7687"

#RUN service neo4j start

RUN printf "\n[mysqld]\nsecure-file-priv = \"\"" >> /etc/mysql/my.cnf
#RUN mkdir /var/lib/mysql/trainbenchmark
RUN usermod -d /var/lib/mysql/ mysql
#RUN chown mysql:mysql /var/lib/mysql/trainbenchmark

ENV MYSQLD_OPTS="--skip-grant-tables"

RUN service mysql restart &&  \
    mysql -u root -e "DROP USER 'root'@'localhost'; CREATE USER 'root'@'%' IDENTIFIED BY ''; GRANT ALL PRIVILEGES ON *.* TO 'root'@'%'; FLUSH PRIVILEGES;" && \
    service mysql restart

WORKDIR /home/neo4j
RUN wget https://neo4j.com/artifact.php?name=neo4j-community-5.16.0-unix.tar.gz -O neo4j.tar.gz
RUN tar xf /home/neo4j/neo4j.tar.gz

EXPOSE 8080
EXPOSE 3306
EXPOSE 7474
EXPOSE 7473
EXPOSE 7687

WORKDIR /benchmark

COPY . /benchmark

RUN mv /home/neo4j/neo4j-community-5.16.0 /benchmark/neo4j-server/

RUN dos2unix ./gradlew
RUN dos2unix ./trainbenchmark-format-sql/scripts/*

CMD ["/bin/bash"]