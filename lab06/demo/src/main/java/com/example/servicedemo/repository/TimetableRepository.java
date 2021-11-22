package com.example.servicedemo.repository;

import com.example.servicedemo.Timetable.Timetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Integer> {
    Timetable findByID(int id);
}
