package com.pjwstk.sakila.filmsupdater.selfTest;

import com.pjwstk.sakila.diagnostics.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@NoArgsConstructor
public class DbConnectionSelfTest implements iSelfTest {

    private final EntityManager entityManager;

    @Override
    public SelfTestResult run() {
        SelfTestResult result;

        try {
            entityManager.createNativeQuery("select 1 from film limit 1").getSingleResult();
            result = SelfTestResult.builder()
                    .name("Database connection test")
                    .description("Checks if there is a connection with database")
                    .passed(true)
                    .errors(null)
                    .build();
        }catch (Exception e){
            result = SelfTestResult.builder()
                    .name("Database connection test")
                    .description("Checks if there is a connection with database")
                    .passed(false)
                    .errors(List.of("No connection with databse",e.getMessage()))
                    .build();
        }
        return result;
    }
}
