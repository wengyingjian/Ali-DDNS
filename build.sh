mvn clean package -DskipTests  -U

cp .asset/*.jar docker/app.jar

cd docker

docker build -t ali-ddns .
docker tag ali-ddns:latest wengyingjian/ali-ddns:latest
docker push wengyingjian/ali-ddns:latest

