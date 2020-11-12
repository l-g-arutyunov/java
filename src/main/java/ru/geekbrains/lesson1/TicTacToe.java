package ru.geekbrains.lesson1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe extends JFrame {

    public static TicTacToe ticTacToe;
    public static final int SIZE = 15;
    public static final int DOTS_TO_WIN = 3;
    public static final int WINDOW_SIZE = 750;
    public static final int CELL_SIZE = WINDOW_SIZE / SIZE;

    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final int LINE_LIMIT = 15;

    private static Image O;
    private static Image X;
    private static boolean myTurn;
    private static int x;
    private static int y;

    public static char[][] map;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) throws Exception {

        X = ImageIO.read(TicTacToe.class.getResourceAsStream("X.png"));
        O = ImageIO.read(TicTacToe.class.getResourceAsStream("O.jpg"));

        TicTacToe gameWindow = new TicTacToe();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200,100);
        gameWindow.setSize(WINDOW_SIZE+10, WINDOW_SIZE+50);
        gameWindow.setResizable(false);
        GameField gameField = new GameField();
        gameField.addMouseListener(new MouseAdapter() {
                                       @Override
                                       public void mousePressed(MouseEvent e) {
                                           x = e.getX() / CELL_SIZE;
                                           y = e.getY() / CELL_SIZE;
                                           //super.mousePressed(e);
                                       }
                                   });


                gameWindow.add(gameField);
        gameWindow.setVisible(true);

        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X, x, y, map)) {
                System.out.println("Человек - умнейшее существо на планете! WIN");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O, x, y, map)) {
                System.out.println("Леонид победил! Потому что он написал алгоритм. DEFEAT");
                break;
            }

            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра завершена");
        scanner.close();
    }

    private static boolean isMapFull() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (map[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    private static boolean checkWin(char symbol, int valueOfX, int valueOfY, char[][] map1) {
        int countForWin = 0;
        //проверка горизонтальной линии
        //Рассчтитывем X нулевое и X максимальное, как координаты начала и конца проверочной линии
        int xMin = valueOfX - (DOTS_TO_WIN - 1) > 0 ? valueOfX - (DOTS_TO_WIN - 1) : 0;
        int xMax = valueOfX + (DOTS_TO_WIN - 1) < SIZE-1 ? valueOfX + (DOTS_TO_WIN - 1) : SIZE - 1;
        for (int i = xMin; i <= xMax ; i++) {
            if (map1[valueOfY][i] == symbol) {
                countForWin++;
                if (countForWin == DOTS_TO_WIN) {
                    return true;
                }
                continue;
            }
            countForWin = 0;
            //В случае, если остатка проверочной линии не хватает для победы, то прерываем цикл
            if (xMax - i < DOTS_TO_WIN) {
                break;
            }
        }

        //проверка верикальной линии
        //Аналогично с горизонтальной, рассчтитывем Y нулевое и Y максимальное
        int yMin = valueOfY - (DOTS_TO_WIN - 1) > 0 ? valueOfY - (DOTS_TO_WIN - 1) : 0; //top size
        int yMax = valueOfY + (DOTS_TO_WIN - 1) < SIZE-1 ? valueOfY + (DOTS_TO_WIN - 1) : SIZE - 1; //bottom size
        countForWin = 0;
        for (int i = yMin; i <= yMax ; i++) {
            if (map1[i][valueOfX] == symbol) {
                countForWin++;
                if (countForWin == DOTS_TO_WIN) {
                    return true;
                }
                continue;
            }
            countForWin = 0;
            if (yMax - i < DOTS_TO_WIN) {
                break;
            }
        }
        //проверка диагонали 11-5 часов
        //рассчитываем количество шагов, исходя из минимальной разницы начала и конца проверочных линий(+ 1 сама клетка)
        //int stepCount = (Math.min(yMax, xMax) - Math.max(xMin, yMin)) + 1;
        //int stepCount = (Math.min(yMax - yMin - y, xMax - xMin - x)) + 1;
        int stepCount = Math.min(valueOfX - xMin, valueOfY - yMin) + Math.min(xMax - valueOfX, yMax - valueOfY) + 1;
        countForWin = 0;
      /* Рассчитываем нулевые координаты диагональной проверочной линии:
        Берем x и y как расстояние от левой и верхней грани, получаем минимальное(максимально возможная точка отсчета)
        Корректируем точку отсчета на логические грани провеорчных линий(что бы не проверить больше чем нужно).*/
        int d1 = Math.min(valueOfY, valueOfX) - Math.min(yMin, xMin);
        int yMinD1 = valueOfY - d1;
        int xMinD1 = valueOfX - d1;
        for (int i = 0; i < stepCount; i++) {
            if (map1[yMinD1+i][xMinD1+i] == symbol) {
                countForWin++;
                if (countForWin == DOTS_TO_WIN) {
                    return true;
                }
                continue;
            }
            countForWin = 0;
            if (stepCount - i < DOTS_TO_WIN) {
                break;
            }
        }
        //проверка диагонали 1 - 7 часов
        //Аналогично 11 - 5, но инвертируем x - подобные значения, для смены главной х-грани с левой на правую
        //stepCount = (Math.min(yMax, SIZE-1-xMin) - Math.max(SIZE-1-xMax, yMin)) + 1;
        stepCount = Math.min(xMax - valueOfX, valueOfY - yMin) + Math.min(valueOfX - xMin, yMax - valueOfY) + 1;
        countForWin = 0;
        int d2 = Math.min(valueOfY, SIZE-1-valueOfX) - Math.min(yMin, SIZE-1-xMax);
        int yMinD2 = valueOfY - d2;
        int xMinD2 = valueOfX + d2;
        for (int i = 0; i < stepCount; i++) {
            if (map1[yMinD2+i][xMinD2-i] == symbol) {
                countForWin++;
                if (countForWin == DOTS_TO_WIN) {
                    return true;
                }
                continue;
            }
            countForWin = 0;
            if (stepCount - i < DOTS_TO_WIN) {
                break;
            }
        }
        return false;
    }

    private static void aiTurn() {
    /*  0)Поиск победы в 1 ход
        1)Блокировка победы соперника
        2)Поиск подебы в 2 и более хода
        3)Рандомный ход*/
//    PS как же неудобно манипулировать char, если рассматриваем не текст

        char[][] doubleOfMap  = deepCopyOfArray(map);
        Map<String, Integer> resultStructure;
        //0
        resultStructure=  checkNextTurn('O' ,doubleOfMap);
        if (resultStructure.get("result") == 1) {
            x = resultStructure.get("x");
            y = resultStructure.get("y");
            map[y][x] = DOT_O;
            return;
        }
        //1
        resultStructure=  checkNextTurn('X' ,doubleOfMap);
        if (resultStructure.get("result") == 1) {
            x = resultStructure.get("x");
            y = resultStructure.get("y");
            map[y][x] = DOT_O;
            return;
        }

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        map[x][y] = DOT_O;
    }

    private static Map checkNextTurn(char c, char[][] doubleOfMap) {
        char[][] twoDoubleOfMap = deepCopyOfArray(doubleOfMap);
        Map<String, Integer> resultStructure = new HashMap<String, Integer>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (doubleOfMap[i][j] == c) {
                    if (i == 0 || i == SIZE - 1) {
                        if (j !=  0) {
                            if (doubleOfMap[i][j - 1] == DOT_EMPTY) {
                                twoDoubleOfMap[i][j - 1] = c;
                                if (checkWin(c, j - 1, i, twoDoubleOfMap)) {
                                    resultStructure.put("result", 1);
                                    resultStructure.put("x", i);
                                    resultStructure.put("y", j - 1);
                                    return resultStructure;
                                }
                                twoDoubleOfMap = deepCopyOfArray(doubleOfMap);
                            }
                        }
                        if (j != SIZE - 1 ) {
                            if (doubleOfMap[i][j + 1] == DOT_EMPTY) {
                                twoDoubleOfMap[i][j + 1] = c;
                                if (checkWin(c, j + 1, i, twoDoubleOfMap)) {
                                    resultStructure.put("result", 1);
                                    resultStructure.put("x", i);
                                    resultStructure.put("y", j + 1);
                                    return resultStructure;
                                }
                                twoDoubleOfMap = deepCopyOfArray(doubleOfMap);
                            }
                        }
                        continue;
                    }

                    if (j == 0 || j == SIZE - 1) {
                        if (i != 0) {
                            if (doubleOfMap[i - 1][j] == DOT_EMPTY) {
                                twoDoubleOfMap[i - 1][j] = c;
                                if (checkWin(c, j, i - 1, doubleOfMap)) {
                                    resultStructure.put("result", 1);
                                    resultStructure.put("x", i - 1);
                                    resultStructure.put("y", j);
                                    return resultStructure;
                                }
                                twoDoubleOfMap = deepCopyOfArray(doubleOfMap);
                            }
                        }
                        if (i != SIZE - 1 ) {
                            if (doubleOfMap[i + 1][j] == DOT_EMPTY) {
                                twoDoubleOfMap[i + 1][j] = c;
                                if (checkWin(c, j, i + 1, twoDoubleOfMap)) {
                                    resultStructure.put("result", 1);
                                    resultStructure.put("x", i + 1);
                                    resultStructure.put("y", j);
                                    return resultStructure;
                                }
                                twoDoubleOfMap = deepCopyOfArray(doubleOfMap);
                            }
                        }
                        continue;
                    }

                    for (int i1 = -1; i1 < 2; i1++) {
                        for (int j1 = -1; j1 < 2; j1++) {
                            if (i1 == 0 && j1 == 0 || twoDoubleOfMap[i + i1][j + j1] != DOT_EMPTY) {
                                continue;
                            }
                            twoDoubleOfMap[i + i1][j + j1] = c;
                            if (checkWin(c, j + j1, i + i1, twoDoubleOfMap)) {
                                resultStructure.put("result", 1);
                                resultStructure.put("x", j + j1);
                                resultStructure.put("y", i + i1);
                                return resultStructure;
                            }
                            twoDoubleOfMap = deepCopyOfArray(doubleOfMap);
                        }
                    }
                }
            }
        }
        resultStructure.put("result", 0);
        return resultStructure;
    }

    private static char[][] deepCopyOfArray(char[][] array) {
        char[][] copyArray = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            copyArray[i] = array[i].clone();
        }
        return copyArray;
    }

    private static void humanTurn() throws InterruptedException {
        x = -1;
        y = -1;
        while (!isCellValid(x, y)) {
            Thread.sleep(10);
        }
        map[y][x] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE) return false;
        if (y < 0 || y >= SIZE) return false;
        if (map[y][x] != DOT_EMPTY) return false;
        return true;
    }

    private static void printMap() {

//        Верхний отступ
        for (int i = 0; i < LINE_LIMIT; i++) {
            System.out.println();
        }

//        Верхняя "Легенда"
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
//        Выводим игровое поле
        for (int y = 0; y < SIZE; y++) {
            System.out.print((y+1) + " ");
            for (int x = 0; x < SIZE; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void onRepaint(Graphics g) {
        for (int i = 1; i < SIZE; i++) {
            g.drawLine(0, i*CELL_SIZE, WINDOW_SIZE, i*CELL_SIZE);
            g.drawLine(i*CELL_SIZE, 0,i*CELL_SIZE, WINDOW_SIZE);
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[j][i] == 'X') {
                    g.drawImage(X,i*CELL_SIZE+5, j*CELL_SIZE+5, CELL_SIZE-5, CELL_SIZE-5, null);
                }
                if (map[j][i] == 'O') {
                    g.drawImage(O,i*CELL_SIZE+5, j*CELL_SIZE+5, CELL_SIZE-5, CELL_SIZE-5, null);
                }
            }
        }


    }

    private static class GameField extends JPanel{

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }

    }
}



