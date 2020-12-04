package io.github.zhoudyme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 使用PrepareStatement测试插入数据
 *
 * @author zhoudy
 * @date 2020/12/04
 */
public class JdbcPrepareStatementInsertDemo {

    private static final int BATCH_COUNT = 10000;
    private static final Integer TOTAL_COUNT = BATCH_COUNT * 100;

    public static void main(String[] args) throws SQLException {
        insert();
    }

    private static void insert() throws SQLException {
        String insertSql = "INSERT INTO `order` ( `p_create_datetime`, `p_name`, `p_update_datetime`, `p_version`, `order_status`, `order_price`, `user_id` ) VALUES ( NOW(), ?, NOW(), ?, ?, ?, ? )";
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
