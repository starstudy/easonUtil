package com.eason.serialize.protostuff;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sxx
 * @brief 测试bean
 * @details
 * 关于@Tag,要么所有属性都有@Tag注解,要么都没有,不能一个类中只有部分属性有@Tag注解
 * @date 2018-07-18 14:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String name;

    private String studentNo;

    private int age;

    private String schoolName;

    private List<Student> schoolmates;

    private Teacher teacher;

}
