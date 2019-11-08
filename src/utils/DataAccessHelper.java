/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class này giúp tạo hoặc đóng kết nối với cơ sở dữ liệu. Ở đây thực hiện kết nối với csdl từ Sql server
 * @author Raph
 */
public class DataAccessHelper {


    /**
     * Hàm này để tạo kết nối với cơ sở dữ liệu
     * @return Connection
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String hostName = "localhost";
        String sqlInstanceName = "SANTIAGO";        // tên server
        String database = "LMS_db";                 // tên database
        String userName = "sa";                     //
        String password = "kingdom";                //

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433"             
                + ";instance=" + sqlInstanceName + ";databaseName=" + database;     // port 1433

        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        
        return conn;
    }

}
