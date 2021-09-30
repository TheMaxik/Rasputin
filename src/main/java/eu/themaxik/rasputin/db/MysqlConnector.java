package eu.themaxik.rasputin.db;

import java.sql.*;

public class MysqlConnector {


    private Connection connection;

    public MysqlConnector(String host, int port, String username, String password, String db){
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

        System.out.println("Connecting database...");

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
            this.connection = connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public void insertServer(String host,String motd, int maxPlayers, boolean whitelist, String version){
        try {
            PreparedStatement ps = connection
                    .prepareStatement
                            ("INSERT IGNORE INTO servers (ip,motd,maxplayers,whitelist,version,players) VALUES (?,?,?,?,?)");
            ps.setString(1, host);
            ps.setString(2, motd);
            ps.setInt(3, maxPlayers);
            ps.setBoolean(4, false);
            ps.setString(5, version);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
