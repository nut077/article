Environment variables: jasypt.encryptor.password=secretkey<br/>
docker run --name articledb -e MYSQL_USER=freedom -e MYSQL_PASSWORD=123 -e MYSQL_ROOT_PASSWORD=123 -e MYSQL_DATABASE=article -e TZ=Asia/Bangkok -p 3300:3306 -d --restart=always mysql:8.0