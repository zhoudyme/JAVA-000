import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 配置 Hikari 连接池，改进上述操作。
 *
 * @author zhoudy
 * @date 2020/11/18
 */
public class HikariCPDemo {

    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo?serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=UTF-8&rewriteBatchedStatements=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        HikariDataSource ds = new HikariDataSource(config);
        Connection connection = ds.getConnection();
        Statement statement = connection.createStatement();
        //添加
        statement.executeUpdate("INSERT INTO `user` (`id`, `name`) VALUES ('1', 'zhoudy');");
        //查询
        ResultSet rs = statement.executeQuery("select * from user;");
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id")
                    + "，name:" + rs.getString("name"));
        }
        //修改
        statement.executeUpdate("update user set name = 'zhou' where id = 1;");
        //删除
        statement.executeUpdate("delete from user where id = 1;");
        statement.close();
        connection.close();
        ds.close();
    }
}
