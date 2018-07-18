package com.eason.serialize.protostuff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sxx
 * @brief 测试ProtoStuff
 * @details
 * @date 2018-07-18 14:33
 */
public class TestProtoStuff {

    public static void main(String[] args) {
        Student student = new Student();
        student.setName("lance");
        student.setAge(28);
        student.setStudentNo("2011070122");
        student.setSchoolName("BJUT");

        Student student2 = new Student();
        student2.setName("lance");
        student2.setAge(28);
        student2.setStudentNo("2011070122");
        student2.setSchoolName("BJUT");

        Student student3 = new Student();
        student3.setName("lance");
        student3.setAge(28);
        student3.setStudentNo("2011070122");
        student3.setSchoolName("BJUT");

        List<Student> studentLit = new ArrayList<Student>();
        studentLit.add(student2);
        studentLit.add(student3);
        student.setSchoolmates(studentLit);

        Teacher teacher = new Teacher();
        teacher.setAge(28);
        teacher.setName("帅哥");
        student.setTeacher(teacher);

        byte[] serializerResult = ProtostuffUtil.serializer(student);
        byte[] jsonResult = ProtostuffUtil.serializeJson(student);

        System.out.println("serializer result Json:" + Arrays.toString(jsonResult));
        System.out.println("serializer result Json:" + new String(jsonResult));


        System.out.println("serializer result:" + Arrays.toString(serializerResult));

        Student deSerializerResult = ProtostuffUtil.deserializer(serializerResult,Student.class);

        System.out.println("deSerializerResult:" + deSerializerResult.toString());

    }
}
