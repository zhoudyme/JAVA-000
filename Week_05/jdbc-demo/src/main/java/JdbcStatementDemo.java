import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 使用 JDBC 原生接口，实现数据库的增删改查操作
 *
 * @author zhoudy
 * @date 2020/11/18
 */
public class JdbcStatementDemo {


    public static void main(String[] args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
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
    }


}
