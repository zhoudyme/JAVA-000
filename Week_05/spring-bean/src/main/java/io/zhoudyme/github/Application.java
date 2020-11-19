package io.zhoudyme.github;

import io.zhoudyme.github.bean.StudentXml;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import io.zhoudyme.github.bean.StudentAnnotation;
import io.zhoudyme.github.config.StudentAnnotationConfig;

/**
 * @author zhoudy
 * @date 2020/11/18
 */
public class Application {


    public static void main(String[] args) {

        // xml配置方式
        ApplicationContext context1 = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentXml studentXml = (StudentXml) context1.getBean("studentXml");
        System.out.println("我是xml配置方式生成的：" + studentXml);

        //自动注解方式
        AnnotationConfigApplicationContext context2 = new AnnotationConfigApplicationContext(StudentAnnotationConfig.class);
        StudentAnnotation studentAnnotation = context2.getBean(StudentAnnotation.class);
        System.out.println("我是自动注解方式生成的：" + studentAnnotation);
        context2.close();

    }
}
