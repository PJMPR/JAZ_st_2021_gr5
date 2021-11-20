package com.example.demo.timetable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TIMETABLE")
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "timetable_id")
    private int timetable_id;

    @OneToMany(mappedBy = "timetable")
    private List<Installment> instalments = new ArrayList<>();

    public List<Installment> getInstalments() {
        return instalments;
    }
}
