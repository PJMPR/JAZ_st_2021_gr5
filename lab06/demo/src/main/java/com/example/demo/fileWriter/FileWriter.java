package com.example.demo.fileWriter;

import com.example.demo.service.TimetableService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileWriter {
    void getFile(HttpServletResponse response, int id, TimetableService timetableService) throws IOException;
}
