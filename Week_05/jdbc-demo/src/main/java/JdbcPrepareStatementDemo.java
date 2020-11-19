import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使用事务，PrepareStatement 方式，批处理方式，改进上述操作
 *
 * @author zhoudy
 * @date 2020/11/18
 */
public class JdbcPrepareStatementDemo {


    public static void main(String[] args) throws Exception {
//        insert();
//        update();
//        query();
//        delete();
    }

    private static void query() throws SQLException {
        String querySql = "select * from user";
        Connection connection = JdbcUtils.getConnection();
        //设置是否自动提交事务
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(querySql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id")
                    + "，name:" + rs.getString("name"));
        }
        connection.commit();
        connection.setAutoCommit(true);
        pstmt.close();
        connection.close();
    }

    private static void delete() throws SQLException {
        String deleteSql = "delete from user";
        Connection connection = JdbcUtils.getConnection();
        //设置是否自动提交事务
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(deleteSql);
        System.out.println("成功删除" + pstmt.executeUpdate() + "条数据");
        connection.commit();
        connection.setAutoCommit(true);
        pstmt.close();
        connection.close();
    }

    private static void update() throws SQLException {
        String updateSql = "update user set name = ?";
        Connection connection = JdbcUtils.getConnection();
        //设置是否自动提交事务
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(updateSql);
        pstmt.setString(1, "zhoudyy");
        System.out.println("成功更新" + pstmt.executeUpdate() + "条数据");
        connection.commit();
        connection.setAutoCommit(true);
        pstmt.close();
        connection.close();
    }

    private static void insert() throws SQLException {
        String insertSql = "insert into user (id,name) values(?,?)";
        Connection connection = JdbcUtils.getConnection();
        //设置是否自动提交事务
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(insertSql);
        for (int i = 0; i < 10000; i++) {
            pstmt.setInt(1, i);
            pstmt.setString(2, "zhoudy" + i);
            pstmt.addBatch();//添加到同一个批处理中
        }
        pstmt.executeBatch();//执行批处理
        connection.commit();
        connection.setAutoCommit(true);
        pstmt.close();
        connection.close();
    }

}
