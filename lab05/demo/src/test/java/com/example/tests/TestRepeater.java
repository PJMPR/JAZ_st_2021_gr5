package com.example.tests;

import org.example.repeaters.IRepeaterExceptionRegistry;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class TestRepeater extends Mockito {

    @Mock
    IRepeaterExceptionRegistry registry;

    @Test
    public void test_should_check_if_repeater_work_correctly(){
            when(registry.EntryFor(any(NullPointerException.class)))
                    .thenReturn(new IRepeaterExceptionRegistry.RegistryEntry(NullPointerException.class.getName(), 1,2));

    }
}
