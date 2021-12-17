package com.pjwstk.sakila.logic.safe;

import com.pjwstk.sakila.logic.safe.repeaters.IRepeater;
import com.pjwstk.sakila.logic.safe.repeaters.Repeater;
import com.pjwstk.sakila.logic.safe.repeaters.RepeaterExceptionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SafeInvokerConfiguration {

    @Bean
    public RepeaterExceptionRegistry getRegistry(){
        return RepeaterExceptionRegistry.getInstance();
    }
    @Bean
    @Scope("prototype")
    public Repeater repeater(RepeaterExceptionRegistry registry){
        return new Repeater(registry);
    }

    @Bean
    @Scope("prototype")
    public SafeInvoker safeInvoker(IRepeater repeater){
        return new SafeInvoker(repeater);
    }
}
