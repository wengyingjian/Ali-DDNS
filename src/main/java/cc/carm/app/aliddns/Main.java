package cc.carm.app.aliddns;

import cc.carm.app.aliddns.conf.AppConfig;
import cc.carm.app.aliddns.manager.RequestManager;
import cc.carm.app.aliddns.utils.TimeDateUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static RequestManager requestManager;

    public static AppConfig config;

    public static void main(String[] args) throws InterruptedException {

        print("-------------------------------------------");
        print("阿里云服务 DDNS更新器");
        print("项目地址 https://git.carm.cc/AliDDNS-Updater");
        print("-------------------------------------------");

        print("初始化配置文件...");
        config = AppConfig.init();

        print("初始化记录请求管理器...");
        requestManager = new RequestManager(config);

        print();
        print("所有任务已设定为每 " + TimeDateUtils.toDHMSStyle(config.getPeriod()) + " 进行一次更新。");
        print("-------------------------------------------");

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getRequestManager().doUpdate();
            }
        }, 500, config.getPeriod() * 1000L);

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
        if (config.getDebug()) {
            printWithPrefix("[DEBUG] ", messages);
        }
    }

    public static void severe(String... messages) {
        printWithPrefix("[ERROR] ", messages);
    }

    public static RequestManager getRequestManager() {
        return requestManager;
    }

}
