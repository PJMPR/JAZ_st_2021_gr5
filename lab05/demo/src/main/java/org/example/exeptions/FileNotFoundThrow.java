package org.example.exeptions;

import org.example.SafeInvoker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileNotFoundThrow {
    public void exceptionInvoker() {
        SafeInvoker safeInvoker = new SafeInvoker();
        safeInvoker.invoke(this, "scanNonExistingFile");
    }

    public void scanNonExistingFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(String.valueOf(1)));
    }
}
