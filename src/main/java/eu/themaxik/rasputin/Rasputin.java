package eu.themaxik.rasputin;

import eu.themaxik.rasputin.classes.Worker;
import eu.themaxik.rasputin.db.MysqlConnector;
import eu.themaxik.rasputin.util.Config;
import eu.themaxik.rasputin.util.IPGenerator;
import eu.themaxik.rasputin.util.PrompColor;
import lombok.Getter;
import me.dilley.MineStat;

import java.util.concurrent.ConcurrentLinkedQueue;


public class Rasputin {
    @Getter
    private static boolean end = false;
    private static ConcurrentLinkedQueue<Worker> workers = new ConcurrentLinkedQueue<>();
    private static IPGenerator ipGenerator;
    private static MysqlConnector mysqlConnector;
    private static int threads;
    private static int run = 0;

    public static void main(String[] args) {
        Config config = new Config();
        threads = Integer.parseInt(config.getSetting("rasputin.threads").toString());
        ipGenerator = new IPGenerator(config.getSetting("mysql.host").toString(), threads); //TODO ADD CONFIG FOR REDIS

        String host = config.getSetting("mysql.host").toString();
        int port = Integer.parseInt(config.getSetting("mysql.port").toString());
        String user = config.getSetting("mysql.user").toString();
        String password = config.getSetting("mysql.password").toString();
        String db = config.getSetting("mysql.db").toString();
        mysqlConnector = new MysqlConnector(host, port, user, password, db);


        start();


    }

    private static void handleCallback(MineStat mineStat) {
        if (mineStat.isServerUp()) {
            System.out.println(PrompColor.ANSI_GREEN + mineStat.getAddress() + " is Online!");
            mysqlConnector.insertServer(mineStat.getAddress(), mineStat.getMotd(), mineStat.getMaximumPlayers(), false, mineStat.getVersion());
        }

    }

    private static void startWorker(String ip) {
        Worker w = new Worker();
        w.runAsync(ip, (mineStat -> {
            handleCallback(mineStat);
        }));
        workers.add(w);
        run++;
    }

    private static void start() {
        for (int i = 0; i < threads; i++) {
            startWorker(ipGenerator.getIp());
        }

        do {

            workers.removeIf(w -> !w.isAlive()); //REMOVE DEAD WORKERS

            for(int i = 0; i < threads - workers.size(); i++){
                startWorker(ipGenerator.getIp());
            }

            if (run > 5000) {
                System.out.println(PrompColor.ANSI_BLUE + "Cleared Garbage");
                System.gc();
                run = 0;
            }
        } while (true);

    }


}
