package io.github.zhoudyme;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author zhoudy
 * @date 2020/12/04
 */
public class JdbcUtils {

    private static final String URL = "jdbc:mysql://localhost:3307/sharding_db_order?serverTimezone=UTC&useSSL=false&characterEncoding=UTF-8&rewriteBatchedStatements=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

}
