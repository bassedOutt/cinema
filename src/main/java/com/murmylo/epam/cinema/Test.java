package com.murmylo.epam.cinema;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;


public class Test {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        ClassModifier.modify(myClass);
        System.out.println(myClass.getA());
    }

    public static class MyClass{
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }

    public static class ClassModifier{
        public static void modify(MyClass myClass){
            myClass.setA(5);
        }
    }

}
