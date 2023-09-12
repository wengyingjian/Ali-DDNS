# 阿里云DDNS更新器

阿里云服务，域名DDNS更新器。

## 使用方法

下载后，放到单独的文件夹内，在控制台中执行以下命令即可运行。

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
