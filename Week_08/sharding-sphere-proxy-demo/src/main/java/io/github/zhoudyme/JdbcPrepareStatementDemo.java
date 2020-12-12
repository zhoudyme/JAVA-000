package io.github.zhoudyme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作
 *
 * @author zhoudy
 * @date 2020/12/12
 */
public class JdbcPrepareStatementDemo {

    private static final int BATCH_COUNT = 10;
    private static final Integer TOTAL_COUNT = BATCH_COUNT * 100;

    public static void main(String[] args) throws Exception {
//        insert();
//        update();
//        query();
//        delete();
    }

    private static void query() throws SQLException {
        String querySql = "select * from t_order limit 0,10";
        Connection connection = JdbcUtils.getConnection();
        //设置是否自动提交事务
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(querySql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("p_id:" + rs.getString("p_id")
                    + "，user_id:" + rs.getString("user_id"));
        }
        connection.commit();
        connection.setAutoCommit(true);
        pstmt.close();
        connection.close();
    }

    private static void delete() throws SQLException {
        String deleteSql = "delete from t_order";
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
        String updateSql = "update t_order set order_price = ?";
        Connection connection = JdbcUtils.getConnection();
        //设置是否自动提交事务
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(updateSql);
        pstmt.setString(1, "1000");
        System.out.println("成功更新" + pstmt.executeUpdate() + "条数据");
        connection.commit();
        connection.setAutoCommit(true);
        pstmt.close();
        connection.close();
    }

    private static void insert() throws SQLException {
        String insertSql = "INSERT INTO `t_order` (`p_id`, `p_create_datetime`, `p_name`, `p_update_datetime`, `p_version`, `order_status`, `order_price`, `user_id` ) VALUES ( ?, NOW(), ?, NOW(), ?, ?, ?, ? )";
        Connection connection = JdbcUtils.getConnection();

        //设置是否自动提交事务
        connection.setAutoCommit(false);
        PreparedStatement pstmt = connection.prepareStatement(insertSql);

        LocalDateTime beginLocalDateTime = LocalDateTime.now();
        System.out.println("开始插入数据：" + beginLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        for (int i = 0; i < TOTAL_COUNT; i++) {
            pstmt.setInt(1, i);
            pstmt.setInt(2, i);
            pstmt.setInt(3, i);
            pstmt.setInt(4, i);
            pstmt.setInt(5, i);
            pstmt.setInt(6, i);
            pstmt.addBatch();//添加到同一个批处理中
            if (i % BATCH_COUNT == 0) {
                LocalDateTime tempLocalDateTime = LocalDateTime.now();
                System.out.println("已插入" + i + "条数据，已耗时：" + Duration.between(beginLocalDateTime, tempLocalDateTime).getSeconds() + "秒");
            }
        }
        pstmt.executeBatch();//执行批处理
        connection.commit();
        connection.setAutoCommit(true);

        LocalDateTime endLocalDateTime = LocalDateTime.now();
        System.out.println("插入数据结束：" + endLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("共耗时：" + Duration.between(beginLocalDateTime, endLocalDateTime).getSeconds() + "秒");

        pstmt.close();
        connection.close();
    }

}
