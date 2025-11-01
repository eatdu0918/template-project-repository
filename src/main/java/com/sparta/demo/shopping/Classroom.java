package com.sparta.demo.shopping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Classroom {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "classroom") // 1 : 학생(N) 관계
    private List<Student> students = new ArrayList<>();
}
