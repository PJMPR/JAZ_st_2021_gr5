package com.pjwstk.sakila.diagnostics.selftest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


public abstract class SelfTestBase implements iSelfTest{

    String name;
    String description;
}
