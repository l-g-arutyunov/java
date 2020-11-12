package ru.geekbrains.lesson6;

public class Test {

    public static void main(String[] args) {
        Cat cat1 = new Cat(200, 2);
        Cat cat2 = new Cat(300, 3);

        Dog dog1 = new Dog(500, 10, 0.5);
        Dog dog2 = new Dog(600, 10, 0.5);

        System.out.println("Результат прыжка " + cat1 + " : " + dog1.jump(1));
    }

}
