package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DoSomethingBad {
    public void doSomethingSafe(Integer integer) {
        SafeInvoker safeInvoker = new SafeInvoker();
        safeInvoker.invoke(this, "doSomethingDangerous");

    }

    public void doSomethingDangerous() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(String.valueOf(1)));
    }
}
