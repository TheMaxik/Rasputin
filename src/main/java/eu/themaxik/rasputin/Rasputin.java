package eu.themaxik.rasputin;

import eu.themaxik.rasputin.classes.Threader;
import eu.themaxik.rasputin.util.Config;
import eu.themaxik.rasputin.util.IPGenerator;
import eu.themaxik.rasputin.db.MysqlConnector;
import lombok.Getter;


public class Rasputin {
    @Getter
    private static boolean end = false;

    public static void main(String[] args) {
        Config config = new Config();
        String startIP = config.getSetting("rasputin.startip").toString();
        String endIP = config.getSetting("rasputin.endip").toString();

        String host = config.getSetting("mysql.host").toString();
        int port = Integer.parseInt(config.getSetting("mysql.port").toString());
        String user = config.getSetting("mysql.user").toString();
        String password = config.getSetting("mysql.password").toString();
        String db = config.getSetting("mysql.db").toString();
        MysqlConnector mysqlconnector = new MysqlConnector(host, port, user, password, db);

        int threads = Integer.parseInt(config.getSetting("rasputin.threads").toString());

        IPGenerator ipGenerator = new IPGenerator(startIP, endIP);
        for(int i = 0; i < threads; i++){
            Threader t = new Threader("" + i, ipGenerator, mysqlconnector);
            t.start();
        }
    }


}
