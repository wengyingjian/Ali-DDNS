package cc.carm.app.aliddns.model;

import cc.carm.app.aliddns.Main;
import cc.carm.app.aliddns.conf.AppConfig;

import java.util.LinkedHashMap;

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
        UpdateRequest request = new UpdateRequest(
                appConfig.getAccessKey(),
                appConfig.getAccessSecret(),
                appConfig.getDomain(),
                appConfig.getRecord(),
                appConfig.getProtocol()
        );
        data.put(appConfig.getRecord() + "." + appConfig.getDomain(), request);
        return new RequestRegistry(data);
    }


    public static RequestRegistry getInstance() {
        if (registry == null) {
            registry = loadFrom(Main.config);
        }
        return registry;
    }

}
