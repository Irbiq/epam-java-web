package main.com.bsu.musicshop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {


    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {


        ResourceBundle resourceBundle = ResourceBundle.getBundle("configuration/database");
        System.out.println(resourceBundle.getString("db.login"));

        ArrayList<Integer> list = new ArrayList<>();

        Random rd = new Random();
        for (int i = 0; i < 100; ++i) {
            list.add(rd.nextInt(10));

        }
        list.add(11);
        //list.forEach(System.out::println);

        for (int i : list) {
            //    list.removeIf(e-> e ==11 );

        }
        for (int i = 0; i < list.size(); ++i) {
            list.removeIf(e -> e == 9);
        }

        ListIterator<Integer> it = list.listIterator();

        while (it.hasNext()) {
           /* if(it.next().intValue()==0){
                it.remove();
            }*/
            list.removeIf(e -> e == 9);
            if (it.next().intValue() == 9) {
                list.remove(3);
                it.previous();
            }
            if (it.hasNext())
                it.next();
        }
        list.forEach(System.out::println);
    }
}
/*
interface I1{

   default int f() {
       System.out.println("in i1");
       return 1;
   }
}

interface I2{

    default int f(){
        System.out.println("in i2 ");
        return 1;
    }
}


class A implements I1,I2{

   *//* @Override
    public void f() {

    }*//*

    public static void main(String[] args) {

        A a = new A();
        a.f();

    }
    private int f(){
        return 1;
    }


*//*    @Override
    public int f() {
        return 0;
    }*//*
}*/

