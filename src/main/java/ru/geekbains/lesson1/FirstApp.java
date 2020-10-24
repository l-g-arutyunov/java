package ru.geekbains.lesson1;

public class FirstApp {
//    Все примитивные типы
    byte b = 127;
    short s = 127;
    int i = 127;
    long l = 127;
    float f = 127.00f;
    double d = 127.00;
    char c = 'd';
    public static void main(String[] args) {

//        task 3
        System.out.println(calculate(2,1,4,2));
//        task 4
        System.out.println(sumIsBetween10and20(10,11));
//        task 5
        printNegativeOrPositive(-1);
//        task 6
        System.out.println(isNegative(10));
//        task7
        hi_name("leo");
//        task 8
        printLeap(2000);
    }

//    3
    private static int calculate(int a, int b, int c, int d) {
        return a*(b+(c/d));
    }

//    4
    private static boolean sumIsBetween10and20(int a, int b) {
        int sum = a+b;
       if (sum<=20 && sum>=10) {
           return true;
       }
       return false;
    }

//    5
    public static void printNegativeOrPositive (int a) {
//        :)
        if (isNegative(a)) {
            System.out.println("Value " + a + " is negative");
        } else {
            System.out.println("Value " + a + " is positive");
        }
    }

//     6
    private static boolean isNegative(int a) {
        return (a < 0 ? true : false);
    }


//     7
    private static void hi_name(String name) {
        System.out.println("Привет, " + name + "!");
    }

//    8
    private static void printLeap(int year) {
        boolean leap = false;
        if (year%400 == 0) {
           leap = true;
        } else {
            if (year % 4 == 0 && year % 100 != 0) {
                leap = true;
            }
        }

        if (leap) {
            System.out.println(year + " is leap");
        } else {
            System.out.println(year + " is't leap");
        }
    }
}