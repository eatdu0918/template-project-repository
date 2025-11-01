package com.sparta.demo.shopping;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {
    @Id
    private Long id;
    private String name;

    @ManyToOne // 학생(N) : 교실(1) 관계
    @JoinColumn(name = "classroom_id") // DB의 classroom_id 컬럼과 연결
    private Classroom classroom;
}
