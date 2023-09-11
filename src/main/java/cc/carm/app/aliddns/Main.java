package cc.carm.app.aliddns;

import cc.carm.app.aliddns.conf.AppConfig;
import cc.carm.app.aliddns.conf.ServiceConfig;
import cc.carm.app.aliddns.manager.RequestManager;
import cc.carm.app.aliddns.utils.TimeDateUtils;
import cc.carm.lib.configuration.EasyConfiguration;
import cc.carm.lib.configuration.yaml.YAMLConfigProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static RequestManager requestManager;

    public static void main(String[] args) throws InterruptedException {

        print("-------------------------------------------");
        print("阿里云服务 DDNS更新器");
        print("项目地址 https://git.carm.cc/AliDDNS-Updater");
        print("-------------------------------------------");

        print("初始化配置文件...");
        YAMLConfigProvider configuration = EasyConfiguration.from("config.yml");
        configuration.initialize(AppConfig.class);

        print("初始化记录请求管理器...");
        requestManager = new RequestManager();
        int loaded = requestManager.getRequests().size();

        if (loaded < 1) {
            print("    您没有配置任何记录，请检查配置文件！");
            System.exit(0);
        } else {
            print("    初始化完成，共加载了 " + loaded + " 个任务");
        }

        print();
        print("所有任务已设定为每 " + TimeDateUtils.toDHMSStyle(ServiceConfig.PERIOD.getNotNull()) + " 进行一次更新。");
        print("-------------------------------------------");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getRequestManager().doUpdate();
            }
        }, 500, ServiceConfig.PERIOD.getNotNull() * 1000L);

    }


    public static void print(String... messages) {
        if (messages == null || messages.length == 0) {
            System.out.println(" ");
        } else {
            Arrays.stream(messages).forEach(System.out::println);
        }
    }

    public static void printWithPrefix(@NotNull String prefix, String... messages) {
        if (messages == null || messages.length == 0) {
            System.out.println(" ");
        } else {
            Arrays.stream(messages).map(x -> prefix + x).forEach(System.out::println);
        }
    }

    public static void info(String... messages) {
        printWithPrefix("[INFO] ", messages);
    }

    public static void debug(String... messages) {
        if (AppConfig.DEBUG.getNotNull()) printWithPrefix("[DEBUG] ", messages);
    }

    public static void severe(String... messages) {
        printWithPrefix("[ERROR] ", messages);
    }

    public static RequestManager getRequestManager() {
        return requestManager;
    }

}
