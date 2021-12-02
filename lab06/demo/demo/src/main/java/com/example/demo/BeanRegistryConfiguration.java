package com.example.demo;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanRegistryConfiguration {
    @Bean
    public Mapper getMapper(){
        return DozerBeanMapperBuilder.buildDefault();
    }

}
