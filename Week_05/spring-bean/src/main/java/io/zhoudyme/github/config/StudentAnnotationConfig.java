package io.zhoudyme.github.config;

import io.zhoudyme.github.bean.StudentAnnotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoudy
 * @date 2020/11/18
 */
@Configuration
public class StudentAnnotationConfig {

    @Bean
    public StudentAnnotation getStudent() {
        return new StudentAnnotation();
    }
}
