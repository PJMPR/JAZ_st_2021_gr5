package com.lab06.demo.services;

import com.lab06.demo.repositories.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }
}
