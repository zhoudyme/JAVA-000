package io.github.zhoudyme.config;

import io.github.zhoudyme.entity.Klass;
import io.github.zhoudyme.entity.School;
import io.github.zhoudyme.entity.Student;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author zhoudy
 * @date 2020/11/18
 */
@ConfigurationProperties(prefix = "custom")
public class MyProperties {

    @Bean(name = "student100")
    public Student getStudent() {
        return new Student();
    }

    @Bean
    public Klass getKlass() {
        return new Klass();
    }

    @Bean
    public School getSchool() {
        return new School();
    }
}
