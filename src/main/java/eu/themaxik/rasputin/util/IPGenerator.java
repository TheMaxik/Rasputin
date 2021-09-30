package eu.themaxik.rasputin.util;


public class IPGenerator {

    private String lastIP;
    private String endIP;



    public IPGenerator(String startIP, String endIP){
        this.lastIP = startIP;
        this.endIP = endIP;
    }


    public String getIp() {
        String ip = lastIP;
        if(lastIP.equals(endIP) || lastIP == null){
            return null;
        }
        String[] nums = ip.split("\\.");
        int i = (Integer.parseInt(nums[0]) << 24 | Integer.parseInt(nums[2]) << 8
                |  Integer.parseInt(nums[1]) << 16 | Integer.parseInt(nums[3])) + 1;

        // If you wish to skip over .255 addresses.
        if ((byte) i == -1) i++;

        lastIP = String.format("%d.%d.%d.%d", i >>> 24 & 0xFF, i >> 16 & 0xFF,
                i >>   8 & 0xFF, i >>  0 & 0xFF);
        lastIP = checkIfPrivate(lastIP);
        return lastIP;
    }

    //Returns the ip unchanged if not local
    //Returns the next available ip if local
    private String checkIfPrivate(String ip){
        String[] nums = ip.split("\\.");
        if(nums[0].equals("10")){
            return "11.1.1.1";
        }
        if(nums[0].equals("172")){
            int second = Integer.parseInt(nums[1]);
            if(second >= 16 && second <= 31){
                return "172.32.1.1";
            }
        }
        if(nums[0].equals("192") && nums[1].equals("168")){
            return "192.169.1.1";
        }
        if(nums[0].equals("224")){
            System.out.println("The next ip (" + ip + ") looks like the end");
            return null;
        }
        return ip;
    }

}
