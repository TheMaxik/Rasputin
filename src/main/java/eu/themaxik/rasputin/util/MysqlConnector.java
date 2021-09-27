package eu.themaxik.rasputin.util;

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
            Statement statement = connection.createStatement();
            statement.execute("INSERT IGNORE INTO servers (ip,motd,maxplayers,whitelist,version) VALUES ('" + host + "','" + motd + "'," + maxPlayers + "," + whitelist + ",'" + version + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inertScannedIP(String host){
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT IGNORE INTO scanned (ip,lastscanned) VALUES ('" + host + "','" + System.currentTimeMillis() +"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIfScanned(String host){
        try {
            Statement statement = connection.createStatement();
            // Result set get the result of the SQL query
            ResultSet resultSet = statement.executeQuery("SELECT ip FROM scanned WHERE ip='" + host + "'");
            if (!resultSet.next()) {
                return false;
            }
            System.out.println(host + " was alredy scanned <----");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
        return false;
    }

}
