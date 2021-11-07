package org.example;

public class App {

    public static void main(String[] args) {

        int number = 10;
        double d1 = number;
        Double d = new SafeCaster().cast(number, Double.class);
        //if(d!=null)
        System.out.println(d);
        Object tst = "test";
        String s = new SafeCaster().cast(tst, String.class);
        System.out.println(s);

    }


}

class SafeCaster {

    public <T, E> T cast(E obj, Class<T> clazz) {

        T result = null;
        try {
            if (obj != null) result = clazz.cast(obj);
            return result;
        } catch (ClassCastException ex) {
            //ex.printStackTrace();
        }
        return null;
    }

}