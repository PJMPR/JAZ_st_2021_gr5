package org.example;

public class Repeater {

    public static boolean repeater(Supplier method, int counter) {
        while (counter > 0) {
            System.out.println("Retrying, attempts left: " + counter);
            try {
                method.execute();
                return true;
            } catch (Exception exception) {
                try {
                    Thread.sleep(3 + 1000L);
                } catch (Exception exception1) {
                    exception1.printStackTrace();
                }
                counter--;
            }
        }
        return false;
    }
}
