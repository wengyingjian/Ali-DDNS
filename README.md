# 阿里云DDNS更新器

阿里云服务，域名DDNS更新器。

## 使用方法

使用docker启动
```shell
docker run -d \
  --name ali-ddns \
  -e domain=${domain} \
  -e record=${record} \
  -e accessKey=${accessKey} \
  -e accessSecret=${accessSecret} \
  -e period=15 \
  ali-ddns
```
使用docker-compose启动
```yaml
version: '3'
services:
  my-ali-ddns:
    image: ali-ddns
    container_name: ali-ddns
    environment:
      - domain=${domain}
      - record=${record}
      - accessKey=${accessKey}
      - accessSecret=${accessSecret}
      - period=${period}
    restart: always
```
```shell
docker compose up -d
```
使用shell启动

```shell
java -jar AliDDNS-Updater-<version>.jar
```

执行后，您应当看到以下内容输出：



## 配置说明

```
period=15 // 15分钟执行一次
domain=opengpt.pub  //域名
record=nextcloud  //域名记录
accessKey=xx //阿里云账号accessKey
accessSecret=xx //阿里云账号accessSecret
```

