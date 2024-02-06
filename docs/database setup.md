# 데이터베이스(MySQL 8) 수동 생성 명령어
```
DROP USER cqrs@'localhost';
DROP USER cqrs@'%';
FLUSH PRIVILEGES;

CREATE DATABASE cqrs DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER cqrs@'localhost' IDENTIFIED BY 'cqrs';
CREATE USER cqrs@'%' IDENTIFIED BY 'cqrs';

GRANT ALL PRIVILEGES ON cqrs.* TO 'cqrs'@'localhost';
GRANT ALL PRIVILEGES ON cqrs.* TO 'cqrs'@'%';
```
