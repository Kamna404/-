package org.example;

import org.example.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentData {
    private static List<Student> students = new ArrayList<>();

    static {
        students.add(new Student("001", "Alice", 20));
        students.add(new Student("002", "Bob", 22));
    }

    public static List<Student> getAllStudents() {
        return students;
    }

    public static List<Student> getStudentsByName(String name) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                result.add(student);
            }
        }
        return result;
    }

    public static Student getStudentByName(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }

    public static void addStudent(String name, int age) {
        String id = String.format("%03d", students.size() + 1); // 简单生成 ID
        students.add(new Student(id, name, age));
    }
}
