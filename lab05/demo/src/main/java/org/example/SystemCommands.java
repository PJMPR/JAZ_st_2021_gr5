package org.example;

public class SystemCommands {

    public void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean rerun(Supplier m, int counter) {
        if (counter > 0) {
            System.out.println("Trying to reconnect. " + counter +  " reconnection attempts left.");
            try {
                m.execute();
                return true;
            } catch (Exception err) {
                sleep(5);
                rerun(m, --counter );
            }
        }
        return false;
    }
}