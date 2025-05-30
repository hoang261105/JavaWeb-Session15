package com.example.controller;

import com.example.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    private List<Student> students;

    @GetMapping("list-students")
    public String listStudents(Model model) {
        students = new ArrayList<>();
        students.add(new Student(1, "Hoàng", 19, "KS23B", "hoang@gmail.com", "Bắc Giang", "0903599038"));
        students.add(new Student(2, "Phanh", 19, "KS23B", "phanh@gmail.com", "Hải Phòng", "0934389212"));
        model.addAttribute("students", students);
        return "list_students";
    }
}
