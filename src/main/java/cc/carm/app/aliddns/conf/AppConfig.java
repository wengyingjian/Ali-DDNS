package cc.carm.app.aliddns.conf;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class AppConfig {

    private boolean debug = false;
    private int period = 15;
    private String region = "cn-hangzhou";
    private List<String> ipv4Hosts;
    private List<String> ipv6Hosts;
    private Map<String, AliyunConfig> aliyunConfigMap;

    public static AppConfig init() {
        Yaml yaml = new Yaml();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/Users/wengyingjian/IdeaProjects/github/Ali-DDNS/src/main/resources/config.yml");
            return yaml.loadAs(inputStream, AppConfig.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getIpv4Hosts() {
        return ipv4Hosts;
    }

    public void setIpv4Hosts(List<String> ipv4Hosts) {
        this.ipv4Hosts = ipv4Hosts;
    }

    public List<String> getIpv6Hosts() {
        return ipv6Hosts;
    }

    public void setIpv6Hosts(List<String> ipv6Hosts) {
        this.ipv6Hosts = ipv6Hosts;
    }

    public Map<String, AliyunConfig> getAliyunConfigMap() {
        return aliyunConfigMap;
    }

    public void setAliyunConfigMap(Map<String, AliyunConfig> aliyunConfigMap) {
        this.aliyunConfigMap = aliyunConfigMap;
    }
}
