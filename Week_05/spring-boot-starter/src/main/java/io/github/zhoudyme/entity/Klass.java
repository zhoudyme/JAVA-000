package io.github.zhoudyme.entity;

import lombok.Data;

import java.util.List;

/**
 * @author zhoudy
 * @date 2020/11/18
 */
@Data
public class Klass {

    List<Student> students;

    public void dong(){
        System.out.println(this.getStudents());
    }

}
