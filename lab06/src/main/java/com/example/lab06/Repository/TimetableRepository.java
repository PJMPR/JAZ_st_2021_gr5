package com.example.lab06.Repository;

import com.example.lab06.Timetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepository extends CrudRepository<Timetable, Long>{
    public Timetable findById(long id);
}
