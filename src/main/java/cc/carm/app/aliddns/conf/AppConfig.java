package cc.carm.app.aliddns.conf;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class AppConfig {

    private String region = "cn-hangzhou";
    private String protocol = "ipv4";
    private List<String> ipv4Hosts = Arrays.asList("http://members.3322.org/dyndns/getip");
    private List<String> ipv6Hosts = Arrays.asList("https://v6.ip.zxinc.org/getip");

    private int period = 15;
    private String domain;
    private String record;
    private String accessKey;
    private String accessSecret;

    public static AppConfig init() {

        String period = System.getenv("period");
        String domain = System.getenv("domain");
        String record = System.getenv("record");
        String accessKey = System.getenv("accessKey");
        String accessSecret = System.getenv("accessSecret");

        AppConfig appConfig = new AppConfig();
        if (StringUtils.isNotBlank(period)) {
            appConfig.setPeriod(Integer.parseInt(period));
        }
        if (StringUtils.isBlank(domain)
                || StringUtils.isBlank(record)
                || StringUtils.isBlank(accessKey)
                || StringUtils.isBlank(accessSecret)) {
            throw new RuntimeException("参数异常");
        }
        appConfig.setDomain(domain.trim());
        appConfig.setRecord(record.trim());
        appConfig.setAccessKey(accessKey.trim());
        appConfig.setAccessSecret(accessSecret.trim());

        return appConfig;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
