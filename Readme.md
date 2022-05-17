

MySQL 8 works properly. Configuration :
Database Creation Command : create schema broadleaf character set utf8 collate utf8_general_ci;
database.url : jdbc:mysql://localhost:3306/broadleaf?useUnicode=true&characterEncoding=utf8
Rest as instructed in documentation


PostgreSQL works properly. Configuration :
The hibernate dialect should be general one not BroadleafPostgreSQLDialect.
Rest as instructed in documentation


Latest MySQL in docker Tutorial
https://phoenixnap.com/kb/mysql-docker-container

docker pull mysql/mysql-server:latest
docker images
 
sudo mkdir -p /root/docker/mysql/conf.d

sudo nano /root/docker/mysql/conf.d/my-custom.cnf

[mysqld]
max_connections=250

sudo mkdir -p /root/docker/mysql/data

docker run \
--detach \
--name=mysql \
--env="MYSQL_ROOT_PASSWORD=Loc@lhost007" \
--publish 3306:3306 \
--volume=/root/docker/mysql/conf.d:/etc/mysql/conf.d \
--volume=/root/docker/mysql/data:/var/lib/mysql \
mysql/mysql-server:latest


sudo apt-get install mysql-client
docker logs mysql
docker -it mysql bash
mysql -uroot -p
CREATE USER 'root'@'%' IDENTIFIED BY 'Loc@lhost007';
GRANT ALL on *.* to 'root'@'%';



