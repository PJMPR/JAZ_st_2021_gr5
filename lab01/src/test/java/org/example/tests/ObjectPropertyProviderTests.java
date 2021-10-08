package org.example.tests;

import org.example.ObjectPropertyProvider;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ObjectPropertyProviderTests {

    static ObjectPropertyProvider provider = new ObjectPropertyProvider();


    @Test
    public void test_It_should_return_public_getters(){
        List<String> getters = provider.getPublicGetters(Subject.class)
                .stream().map(x->x.getName()).toList();
       assertThat(getters, hasSize(4));
       assertThat(getters, hasItems("getStatus","getNumber","isDone","getName"));
       assertThat(getters, not(hasItems("getTxt","getNothing","emptyMethod","emptyMethodReturn0","getSomething")));
   }


    @Test
    public void test_It_should_return_public_setters(){
        List<String> setters = provider.getPublicSetters(Subject.class)
                .stream().map(x->x.getName()).toList();
        assertThat(setters, hasSize(3));
        assertThat(setters, hasItems("setName","setNumber","setDone"));
        assertThat(setters, not(hasItems("setNothing","setInt","emptyMethod","emptyMethodReturn0","emptyMethodWihParam","setSomthing")));
    }

    @Test
    public void test_It_should_return_fields_for_public_properties(){
        List<String> fields = provider.getFieldsForPublicProperties(Subject.class)
                .stream().map(x->x.getName()).toList();
        assertThat(fields, hasSize(4));
        assertThat(fields, hasItems("name","number","isDone", "status"));
        assertThat(fields, not(hasItems("value", "something")));
    }
}
