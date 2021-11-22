package com.example.servicedemo;

//import com.example.servicedemo.repository.TimetableRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
        //TimetableRepository timetableRepository = context.getBean(TimetableRepository.class);

    }
}
