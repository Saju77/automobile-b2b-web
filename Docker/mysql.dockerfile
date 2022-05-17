version: '3'
services:
  db:
    image: 'mysql/mysql-server:latest'
    volumes:
        -'/root/docker/mysql/data:/var/lib/mysql'
        - '/root/docker/mysql/conf.d:/etc/mysql/conf.d'
    restart: 'always'
    expose:
      - '3306'
    ports:
      - '3306:3306'
    environment:
       MYSQL_ROOT_PASSWORD: 'Loc@lhost007'