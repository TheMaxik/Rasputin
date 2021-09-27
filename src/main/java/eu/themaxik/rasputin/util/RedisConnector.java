package eu.themaxik.rasputin.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisConnector {

    private Jedis jedis;

    public RedisConnector(String host) {
        try {
            jedis = new Jedis(host);
        }catch (JedisConnectionException e){
            e.printStackTrace();
        }

    }

    public void inertScannedIP(String host) {
        jedis.set(host, String.valueOf(System.currentTimeMillis()));
    }

    public boolean checkIfScanned(String host) {
        if (jedis.exists(host)) {
            System.out.println(host + " was alredy scanned <----");
            return true;
        }
        return false;
    }

}
