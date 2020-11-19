package io.github.zhoudyme.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoudy
 * @date 2020/11/18
 */
@Configuration
@ConditionalOnProperty(prefix = "custom", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(MyProperties.class)
public class MyConfiguration {
}
