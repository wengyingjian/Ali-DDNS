package cc.carm.app.aliddns.model;

import cc.carm.app.aliddns.Main;
import cc.carm.app.aliddns.conf.AliyunConfig;
import cc.carm.app.aliddns.conf.AppConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class RequestRegistry {

    protected final LinkedHashMap<String, UpdateRequest> requests;
    protected int updateCount;
    protected boolean hasV6Request;

    private static RequestRegistry registry;

    public RequestRegistry(LinkedHashMap<String, UpdateRequest> requests) {
        this.requests = requests;
        this.updateCount = 1;
        this.hasV6Request = requests.values().stream().anyMatch(UpdateRequest::isIpv6);
    }

    public LinkedHashMap<String, UpdateRequest> listRequests() {
        return requests;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void countUpdate() {
        this.updateCount++;
    }

    public boolean hasV6Request() {
        return hasV6Request;
    }

    public static RequestRegistry loadFrom(AppConfig appConfig) {
        LinkedHashMap<String, UpdateRequest> data = new LinkedHashMap<>();
        for (Map.Entry<String, AliyunConfig> aliyunConfig : appConfig.getAliyunConfigMap().entrySet()) {
            UpdateRequest request = new UpdateRequest(
                    aliyunConfig.getValue().getAccessKey(),
                    aliyunConfig.getValue().getAccessSecret(),
                    aliyunConfig.getValue().getDomain(),
                    aliyunConfig.getValue().getRecord(),
                    aliyunConfig.getValue().getProtocol()
            );
            data.put(aliyunConfig.getKey(), request);
        }
        return new RequestRegistry(data);
    }


    public static RequestRegistry getInstance() {
        if (registry == null) {
            registry = loadFrom(Main.config);
        }
        return registry;
    }

}
