package io.zhoudyme.github.bean;

/**
 * @author zhoudy
 * @date 2020/11/18
 */
public class StudentXml {

    private int age;
    private int classNum;

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
