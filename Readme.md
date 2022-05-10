

MySQL 8 works properly. Configuration :
Database Creation Command : create schema broadleaf character set utf8 collate utf8_general_ci;
database.url : jdbc:mysql://localhost:3306/broadleaf?useUnicode=true&characterEncoding=utf8
Rest as instructed in documentation


PostgreSQL works properly. Configuration :
The hibernate dialect should be general one not BroadleafPostgreSQLDialect.
Rest as instructed in documentation


Latest MySQL in docker Tutorial
https://phoenixnap.com/kb/mysql-docker-container