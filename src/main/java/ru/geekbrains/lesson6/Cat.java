package ru.geekbrains.lesson6;

public class Cat extends Animal {

    public Cat(int limitRun, double limitJump) {
        super(limitRun, 0, limitJump);
    }

    @Override
    public boolean swim(int distance) {
        return false;
    }
}
