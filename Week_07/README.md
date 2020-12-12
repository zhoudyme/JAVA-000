1、按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率  
https://github.com/zhoudyme/JAVA-000/tree/main/Week_07/insert-data  
● 分别测试了使用Statement和PreparedStatement自动提交事务和手动提交事务插入的方式。  
（1）Statement方式  
![Statement数据插入sql](https://github.com/zhoudyme/JAVA-000/blob/main/Week_07/insert-data/src/main/resources/Statement%E6%95%B0%E6%8D%AE%E6%8F%92%E5%85%A5sql.png)  
通过mysql日志可以看到使用Statement方式插入的时候是使用“INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....)”sql的方式一条一条插入的  
![Statement自动提交事务插入时间](https://github.com/zhoudyme/JAVA-000/blob/main/Week_07/insert-data/src/main/resources/Statement%E8%87%AA%E5%8A%A8%E6%8F%90%E4%BA%A4%E4%BA%8B%E5%8A%A1%E6%8F%92%E5%85%A5%E6%97%B6%E9%97%B4.png)   
自动提交事务状态下插入100w条数据耗时1878秒  
![Statement手动提交事务插入时间](https://github.com/zhoudyme/JAVA-000/blob/main/Week_07/insert-data/src/main/resources/Statement%E6%89%8B%E5%8A%A8%E6%8F%90%E4%BA%A4%E4%BA%8B%E5%8A%A1%E6%8F%92%E5%85%A5%E6%97%B6%E9%97%B4.png)   
手动提交事务状态下插入100w条数据耗时144秒  
（2）PreparedStatement方式  
![PreparedStatement数据插入sql](https://github.com/zhoudyme/JAVA-000/blob/main/Week_07/insert-data/src/main/resources/PreparedStatement%E6%95%B0%E6%8D%AE%E6%8F%92%E5%85%A5sql.png)  
通过mysql日志可以看到使用PreparedStatement方式插入的时候是“INSERT INTO table_name (列1, 列2,...) VALUES (值1, 值2,....),(值1, 值2,....)”sql的方式批量插入的  
![PreparedStatement自动提交事务插入时间](https://github.com/zhoudyme/JAVA-000/blob/main/Week_07/insert-data/src/main/resources/PreparedStatement%E8%87%AA%E5%8A%A8%E6%8F%90%E4%BA%A4%E4%BA%8B%E5%8A%A1%E6%8F%92%E5%85%A5%E6%97%B6%E9%97%B4.png)   
自动提交事务状态下插入100w条数据  
![PreparedStatement手动提交事务插入时间](https://github.com/zhoudyme/JAVA-000/blob/main/Week_07/insert-data/src/main/resources/PreparedStatement%E6%89%8B%E5%8A%A8%E6%8F%90%E4%BA%A4%E4%BA%8B%E5%8A%A1%E6%8F%92%E5%85%A5%E6%97%B6%E9%97%B4.png)   
手动提交事务状态下插入100w条数据  
经过几次测试，使用PreparedStatement方式不管使用自动提交事务还是手动提交事务，插入100w条数据的耗时都在20几秒浮动，差距不大  
● 结论  
（1）使用PreparedStatement方式插入数据比使用Statement方式插入数据速度提升明显  
（2）使用Statement方式插入数据的情况下，设置手动提交事务可以大大提升数据插入速度（自动提交事务会每次插入一条数据就往数据库提交一次，手动提交事务会在执行到最后再统一提交插入）  
（3）使用PreparedStatement方式在插入100w条数据的情况下是否自动提交事务对插入速度影响不大  
