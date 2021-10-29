package org.example.queries.search;

public class FunctionsParameters {


    private String fieldName;
    private Funcs function;

    public FunctionsParameters(String fieldName, Funcs function) {
        this.fieldName = fieldName;
        this.function = function;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Funcs getFunction() {
        return function;
    }

    public void setFunction(Funcs function) {
        this.function = function;
    }
}
