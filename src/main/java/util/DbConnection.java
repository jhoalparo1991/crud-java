package util;

import java.sql.*;

public class DbConnection {

    private static String DB="crud_java";
    private static String HOST="localhost";
    private static String USER="root";
    private static String PASSWORD="root";
    private static String PORT="3307";
    private static Connection con = null;
    private static String URL = "jdbc:mysql://" + HOST+":"+PORT+"/"+DB;

    public static Connection getInstance() throws SQLException {
        if(con == null){
            con = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        return con;
    }

}
