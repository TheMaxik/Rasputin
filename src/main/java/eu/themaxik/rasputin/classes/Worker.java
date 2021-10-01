package eu.themaxik.rasputin.classes;

import lombok.SneakyThrows;
import me.dilley.MineStat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;


public class Worker extends Thread {

    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    public void runAsync(String ip, Consumer<MineStat> asyncAction) {
        EXECUTOR.submit(() -> {
            System.out.println("Testing " + ip);
            MineStat ms = new MineStat(ip,25565,30);
            asyncAction.accept(ms);
        });
    }
}
