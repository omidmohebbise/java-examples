package com.javadeveloper.examples.tricky;

public class IntegerCaching {
    public static void main(String[] args){
        Integer num1 = 100;
        Integer num2 = 100;
        if(num1==num2){
            System.out.println("num1 == num2");
        }
        else{
            System.out.println("num1 != num2");
        }

        Integer num3 = 129;
        Integer num4 = 129;
        if(num3==num4){
            System.out.println("num1 == num2");
        }
        else{
            System.out.println("num1 != num2");
        }
    }

    /*
        It will print “num1 == num2”. Whenever two different object references are compared using “==,”
        the value is always “false.” But here, because of the Integer caching, num1 and num2 are autoboxed.
        Thus num1==num2 returns “true”. Integer caching happens only for values between -128 and 127.
     */

}
