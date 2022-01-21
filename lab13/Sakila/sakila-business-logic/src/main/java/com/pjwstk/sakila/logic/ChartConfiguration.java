package com.pjwstk.sakila.logic;

import com.pjwstk.sakila.logic.charts.ChartCreator;
import com.pjwstk.sakila.logic.charts.builder.ChartBuilder;
import com.pjwstk.sakila.logic.safe.SafeInvoking;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ChartConfiguration {

    @Bean
    @Scope("prototype")
    public ChartCreator chartCreator(ChartBuilder builder, SafeInvoking invoker){
        return new ChartCreator(builder,invoker);
    }

    @Bean
    @Scope("prototype")
    public ChartBuilder chartBuilder(){
        return new ChartBuilder();
    }
}
