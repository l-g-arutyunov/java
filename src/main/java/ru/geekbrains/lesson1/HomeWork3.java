package ru.geekbrains.lesson1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeWork3 {
    public static void main(String[] args) {
//        GameGuessTheNumber game = new GameGuessTheNumber();
//        game.startGame();
        WordGame wordGame = new WordGame();
        wordGame.startGame();
    }
}

class GameGuessTheNumber{
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private int hiddenNumber;
    private int tryCount;

    public GameGuessTheNumber() {
        newGame();
    }

    private void newGame() {
        hiddenNumber =  (int) (Math.random() * 10);
        tryCount = 0;
    }

    public void startGame() {
        System.out.println("Было загадано чило от 0 до 9, у Вас 3 попытки его отгадать. Игра началась...");
        do {
            int inputNumber;
            try {
                inputNumber =Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                System.out.println("Число не соответствует формату. Игра завершена!");
                return;
            }
            checkHiddenNumber(inputNumber);
        } while (tryCount < 3);

        System.out.println("Спасибо за игру!");
    }

    private void checkHiddenNumber(int inputNumber) {
        if (inputNumber == hiddenNumber) {
            System.out.println("WIN!!!");
            doYouWant();
            return;
        }
        tryCount ++;
        if (inputNumber > hiddenNumber) {
            System.out.println("Введенное число больше загаданного. Осталось " + (3 - tryCount) + " попыток");
        } else {
            System.out.println("Введенное число меньше загаданного. Осталось " + (3 - tryCount) + " попыток");
        }
        if (tryCount >= 3) {
            System.out.println("defeat :(");
            doYouWant();
        }
    }

    private void doYouWant() {
        System.out.println("Хотите сыграть еще разок?");
        System.out.println("1 – да / 0 – нет");
        try {
            if (Integer.parseInt(reader.readLine()) == 1) {
                tryCount = 0;
                hiddenNumber =  (int) (Math.random() * 10);
                System.out.println("Число перезагадано. Игра началась!!!");
                return;
            };
        } catch (IOException e) {
            System.out.println("Число не соответствует формату. Игра завершена!");
            tryCount = 9999;
            return;
        }
        tryCount = 9999;
    }
}

class WordGame {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String hiddenWord;
    //private String word;

    public WordGame() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
        hiddenWord = words[(int) (Math.random() * 25)];
    }

    public void startGame() {
        System.out.println("Было загадано слово! Игра началась...");
        while (true) {
            String inputWord;
            System.out.println("Введите слово");
            try {
                inputWord = reader.readLine().toLowerCase();
            } catch (IOException e) {
                System.out.println("слово не соответствует формату. Игра завершена!");
                return;
            }
            if (checkHiddenWord(inputWord)) {
                break;
            }
        }
        System.out.println("Спасибо за игру!");
    }

    private boolean checkHiddenWord(String inputWord) {
        if (inputWord.equals(hiddenWord)) {
            System.out.println("Вы угадали!");
            return true;
        }
        int minLength = Math.min(hiddenWord.length(), inputWord.length());
        int i = 0;
        for (; i < minLength; i++) {
            if (inputWord.charAt(i) == hiddenWord.charAt(i)) {
                System.out.print(hiddenWord.charAt(i));
            } else {
                System.out.print("#");
            }
        }
        int closedLetters = 15 - i;
        for (int j = 0; j < closedLetters; j++) {
            System.out.print("#");
        }
        System.out.println();
        return false;
    }
}