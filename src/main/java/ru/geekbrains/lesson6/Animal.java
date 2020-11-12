package ru.geekbrains.lesson6;

public abstract class Animal {
    int limitRun;
    int limitSwim;
    double limitJump;

    public Animal(int limitRun, int limitSwim, double limitJump) {
        this.limitRun = limitRun;
        this.limitSwim = limitSwim;
        this.limitJump = limitJump;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public boolean run(int distance) {
        if (limitRun >= distance) {
            return true;
        }
        return false;
    }

    public boolean swim(int distance) {
        if (limitSwim >= distance) {
            return true;
        }
        return false;
    }

    public boolean jump(int height) {
        if (limitJump >= height) {
            return true;
        }
        return false;
    }


}
