package io.github.zhoudyme;

import java.sql.Connection;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 使用Statement测试插入数据
 *
 * @author zhoudy
 * @date 2020/12/04
 */
public class JdbcStatementInsertDemo {

    private static final int BATCH_COUNT = 10000;
    private static final Integer TOTAL_COUNT = BATCH_COUNT * 100;

    public static void main(String[] args) throws Exception {
        Connection connection = JdbcUtils.getConnection();
        Statement statement = connection.createStatement();

        LocalDateTime beginLocalDateTime = LocalDateTime.now();
        System.out.println("开始插入数据：" + beginLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        //设置是否自动提交事务
        connection.setAutoCommit(false);
        for (int i = 0; i < TOTAL_COUNT; i++) {
            String insertSql = "INSERT INTO `order` ( `p_create_datetime`, `p_name`, `p_update_datetime`, `p_version`, `order_status`, `order_price`, `user_id` ) VALUES ( NOW(), " + i + ", NOW(), " + i + ", " + i + ", " + i + ", " + i + " );";
            statement.executeUpdate(insertSql);
            if (i % BATCH_COUNT == 0) {
                LocalDateTime tempLocalDateTime = LocalDateTime.now();
                System.out.println("已插入" + i + "条数据，已耗时：" + Duration.between(beginLocalDateTime, tempLocalDateTime).getSeconds() + "秒");
            }
        }
        connection.commit();
        connection.setAutoCommit(true);

        LocalDateTime endLocalDateTime = LocalDateTime.now();
        System.out.println("插入数据结束：" + endLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("共耗时：" + Duration.between(beginLocalDateTime, endLocalDateTime).getSeconds() + "秒");
        statement.close();
        connection.close();
    }


}
