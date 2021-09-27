package eu.themaxik.rasputin.classes;

import eu.themaxik.rasputin.util.GarbageCounter;
import eu.themaxik.rasputin.util.IPGenerator;
import eu.themaxik.rasputin.util.MysqlConnector;
import eu.themaxik.rasputin.util.PrompColor;
import me.dilley.MineStat;

public class Threader extends Thread{
    private IPGenerator ipGenerator;
    private MysqlConnector mysqlConnector;
    String name;

    public Threader(String s, IPGenerator ipGenerator, MysqlConnector mysqlConnector){
        name = s;
        this.ipGenerator = ipGenerator;
        this.mysqlConnector = mysqlConnector;
    }

    public void run(){
        System.out.println("Starte Threat: " + name);
        do {
            String ip = ipGenerator.getIp();
            System.out.println("Checking " + ip + " on port 25565");
            MineStat ms = new MineStat(ip, 25565, 30);
            if (ms.isServerUp()) {
                mysqlConnector.insertServer(ms.getAddress(),ms.getMotd(),ms.getMaximumPlayers(),false,ms.getVersion());
                System.out.println(PrompColor.ANSI_GREEN + ip + " is online!" + PrompColor.ANSI_RESET);
            }
            GarbageCounter.increment();
        } while (true);
    }

}
