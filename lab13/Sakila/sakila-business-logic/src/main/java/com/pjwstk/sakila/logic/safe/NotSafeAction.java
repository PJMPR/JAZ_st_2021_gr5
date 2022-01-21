package com.pjwstk.sakila.logic.safe;

@FunctionalInterface
public interface NotSafeAction {
    void execute() throws Exception;
}
