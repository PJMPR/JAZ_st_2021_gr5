package org.example;

public class Behavior {

    public static void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static boolean repeatOperation(ErrorSupplier method, int n) {
        while (n > 0) {
            System.out.println("Attempts left: " + n);
            try {
                method.execute();
                return true;
            } catch (Exception err) {
                wait(3);
                n--;
            }
        }
        return false;
    }

}
