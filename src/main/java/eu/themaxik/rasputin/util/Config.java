package eu.themaxik.rasputin.util;

import java.io.*;
import java.util.Properties;

public class Config {

    private String fileName = "config.yml";
    private Properties properties;

    public Config(){
        File f = new File(fileName);
        properties = new Properties();
        if(!f.exists() || f.isDirectory()) {
            System.out.println(PrompColor.ANSI_RED + "Can't find Config");
            properties.setProperty("mysql.host","localhost");
            properties.setProperty("mysql.port","3306");
            properties.setProperty("mysql.user","root");
            properties.setProperty("mysql.password","");
            properties.setProperty("mysql.db","rasputin");
            properties.setProperty("rasputin.threads", "5000");
            properties.setProperty("rasputin.startip", "1.1.1.1");
            properties.setProperty("rasputin.endip", "255.255.255.254");
            try {
                properties.store(new FileOutputStream(fileName), null);
            } catch (IOException e) {
                System.out.println(PrompColor.ANSI_RED + "Cannot save config file");
                e.printStackTrace();
            }
            return;
        }


        try (FileInputStream fis = new FileInputStream(fileName)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getSetting(String property){
        return properties.get(property);
    }


}
