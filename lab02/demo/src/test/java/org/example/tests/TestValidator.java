package org.example.tests;

import org.example.validators.ValidationResult;
import org.example.validators.Validator;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestValidator {

    public static Validator validator = new Validator();


    @Test
    @Order(1)
    public void test_if_object_with_wrong_fields_is_invalid(){
        SampleObject sample = new SampleObject(null, "test", 11);
        ValidationResult result = validator.validate(sample);
        assertThat(result.isValid(), is(false));
        assertThat(result.getValidatedObject(), notNullValue());
        assertThat(result.getNotValidFields().keySet(),hasSize(3));
    }

    @Test
    public void test_if_object_with_null_name_is_invalid(){
        SampleObject sample = new SampleObject(null, "a@wp.pl", 5);
        ValidationResult result = validator.validate(sample);
        assertThat(result.isValid(), is(false));
        assertThat(result.getValidatedObject(), notNullValue());
        assertThat(result.getNotValidFields().keySet(),hasSize(1));
        assertThat(result.getNotValidFields().keySet(), contains("name"));
        assertThat(result.getNotValidFields().get("name"), hasSize(2));
        assertThat(result.getNotValidFields().get("name"), contains("field is null", "field is empty" ));


    }
    @Test
    public void test_if_object_with_incorrect_email_is_invalid(){

        SampleObject sample = new SampleObject("jan", "a", 5);
        ValidationResult result = validator.validate(sample);
        assertThat(result.isValid(), is(false));
        assertThat(result.getValidatedObject(), notNullValue());
        assertThat(result.getNotValidFields().keySet(),hasSize(1));
        assertThat(result.getNotValidFields().keySet(), contains("email"));
        assertThat(result.getNotValidFields().get("email"), hasSize(1));
        assertThat(result.getNotValidFields().get("email"), contains("email should be in correct format" ));
    }
    @Test
    public void test_if_object_with_wrong_number_renge_is_invalid(){

        SampleObject sample = new SampleObject("jan", "jan@wp.pl", -1);
        ValidationResult result = validator.validate(sample);
        assertThat(result.isValid(), is(false));
        assertThat(result.getValidatedObject(), notNullValue());
        assertThat(result.getNotValidFields().keySet(),hasSize(1));
        assertThat(result.getNotValidFields().keySet(), contains("number"));
        assertThat(result.getNotValidFields().get("number"), hasSize(1));
        assertThat(result.getNotValidFields().get("number"), contains("number is out of range [0,10]" ));

        SampleObject sample2 = new SampleObject("jan", "jan@wp.pl", 11);
        ValidationResult result2 = validator.validate(sample);
        assertThat(result2.isValid(), is(false));
        assertThat(result2.getValidatedObject(), notNullValue());
        assertThat(result2.getNotValidFields().keySet(),hasSize(1));
        assertThat(result2.getNotValidFields().keySet(), contains("number"));
        assertThat(result2.getNotValidFields().get("number"), hasSize(1));
        assertThat(result2.getNotValidFields().get("number"), contains("number is out of range [0,10]" ));
    }
}
