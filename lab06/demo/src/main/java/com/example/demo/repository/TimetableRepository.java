package com.example.demo.repository;

import com.example.demo.timetable.Timetable;
import org.springframework.data.repository.CrudRepository;

public interface TimetableRepository extends CrudRepository<Timetable, Integer> {
    public Timetable findById(int id);
}
