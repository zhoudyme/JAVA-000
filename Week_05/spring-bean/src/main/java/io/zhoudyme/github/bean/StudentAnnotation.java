package io.zhoudyme.github.bean;

import org.springframework.stereotype.Component;

/**
 * @author zhoudy
 * @date 2020/11/18
 */
@Component
public class StudentAnnotation {

    private int age = 18;
    private int classNum = 6;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", classNum=" + classNum +
                '}';
    }
}
