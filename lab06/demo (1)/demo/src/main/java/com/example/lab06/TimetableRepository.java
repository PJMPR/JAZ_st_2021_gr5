package com.example.lab06;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Integer> {
    public Timetable findById(Integer id);
}
