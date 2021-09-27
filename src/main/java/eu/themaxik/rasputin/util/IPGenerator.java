package eu.themaxik.rasputin.util;

import java.util.Random;

public class IPGenerator {

    private Random r;
    private RedisConnector redisConnector;


    public IPGenerator(RedisConnector redisConnector){
        r = new Random();
        this.redisConnector = redisConnector;
    }

    public String getIp(){
        String s;
        do {
            s = String.valueOf(r.nextInt(254));
            if(s.equals("0") || s.equals("127") || s.equals("192") || s.equals("10")) {
                s = "1"; //does not matter its just to prevent the same ip and local network
            }
            s = s + "." + r.nextInt(254) + "." + r.nextInt(254) + "." + r.nextInt(254);
        }while(redisConnector.checkIfScanned(s));
        redisConnector.inertScannedIP(s);
        return s;
    }

    //public String getIp() {
    //    String ip = lastIP;
    //    String[] nums = ip.split("\\.");
    //    int i = (Integer.parseInt(nums[0]) << 24 | Integer.parseInt(nums[2]) << 8
    //            |  Integer.parseInt(nums[1]) << 16 | Integer.parseInt(nums[3])) + 1;
//
    //    // If you wish to skip over .255 addresses.
    //    if ((byte) i == -1) i++;
///
    //    lastIP = String.format("%d.%d.%d.%d", i >>> 24 & 0xFF, i >> 16 & 0xFF,
    //            i >>   8 & 0xFF, i >>  0 & 0xFF);
    //    return lastIP;
    //}

}
