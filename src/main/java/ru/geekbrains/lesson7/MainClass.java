package ru.geekbrains.lesson7;

public class MainClass {
    public static void main(String[] args) {
        Cat[] cats = new Cat[10];
        for (int i = 0; i < cats.length; i++) {
           cats[i] = new Cat("Barsik " + i, 5*i);
        }
        Plate plate = new Plate(100);
        for (Cat cat : cats) {
            cat.eat(plate);
            plate.info();
        }

        for (Cat cat : cats) {
            cat.info();
        }
        plate.info();
    }
}
