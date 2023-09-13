mvn clean package -DskipTests  -U

cp .asset/*.jar docker/app.jar

cd docker

image_name="wengyingjian/ali-ddns:latest"

# 在mac m1平台下构建出x86架构可运行的包
docker buildx build --platform=linux/amd64 . -t "$image_name"

docker push "$image_name"

