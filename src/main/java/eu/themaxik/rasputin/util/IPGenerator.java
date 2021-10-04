package eu.themaxik.rasputin.util;


import eu.themaxik.rasputin.Rasputin;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

public class IPGenerator {

    private Jedis jedis;
    private ArrayList<String> cache = new ArrayList<>();
    private int threads;

    public IPGenerator(String host, int threads) {
        System.out.println("Connecting to Redis");
        this.jedis = new Jedis(host);
        System.out.println(PrompColor.ANSI_GREEN + "Redis connected to " + host);
        this.threads = threads;
    }


    public String getIp() {

        if(!cache.isEmpty()){
            String s = cache.get(0);
            cache.remove(s);
            return s;
        }

        getIpsFromRedis();

        if(!cache.isEmpty()){
            String s = cache.get(0);
            cache.remove(s);
            return s;
        }

        //REDIS EMTPY or smth
        System.out.println(PrompColor.ANSI_RED + "!!!REDIS LOOKS EMPTY!!!");
        return null;
    }

    private void getIpsFromRedis() {
        System.out.println("Loading keys from Redis!");
        int limit = threads;
        if(jedis.dbSize() < threads){
            limit = Integer.parseInt(jedis.dbSize().toString());
        }
        int i = 0;
        Set<String> allAvailableKeys = jedis.keys("*");
        Iterator<String> iterator = allAvailableKeys.iterator();
        while (iterator.hasNext() && i < limit) {
            String key= iterator.next();
            cache.add(key);
            i++;
        }
        jedis.del(cache.toArray(new String[cache.size()]));
    }


}
