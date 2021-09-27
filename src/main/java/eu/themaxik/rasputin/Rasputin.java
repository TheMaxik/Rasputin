package eu.themaxik.rasputin;

import eu.themaxik.rasputin.classes.Threader;
import eu.themaxik.rasputin.util.GarbageCounter;
import eu.themaxik.rasputin.util.IPGenerator;
import eu.themaxik.rasputin.util.MysqlConnector;
import eu.themaxik.rasputin.util.RedisConnector;


public class Rasputin {

    private static MysqlConnector Mysqlconnector;
    private static RedisConnector redisConnector;
    private static IPGenerator ipGenerator;

    private static GarbageCounter garbageCounter;
    private static int garbageLimit = 2000;

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Rasputin.Jar <Threads> [GargabeLimit = 2000]");
            return;
        }
        if(args.length > 1){
            garbageLimit = Integer.parseInt(args[1]);
        }
        //Mysqlconnector = new MysqlConnector("localhost", 3306, "root", "", "rasputin");
        Mysqlconnector = new MysqlConnector("localhost", 3306, "root", "6cs*49wrW!6Nd<qb", "rasputin");
        redisConnector = new RedisConnector("localhost");
        garbageCounter = new GarbageCounter(garbageLimit);
        ipGenerator = new IPGenerator(redisConnector);
        for(int i = 0; i < Integer.parseInt(args[0]); i++){
            Threader t = new Threader("" + i,ipGenerator,Mysqlconnector);
            t.start();
        }
    }


}
