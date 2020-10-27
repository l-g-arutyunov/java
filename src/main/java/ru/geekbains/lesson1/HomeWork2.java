package ru.geekbains.lesson1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HomeWork2 {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(task1()));

        System.out.println(Arrays.toString(task2()));

        System.out.println(Arrays.toString(task3()));

        byte[][] bytesOfTask4 = task4();
        for (byte[] bytes : bytesOfTask4) {
            for (byte aByte : bytes) {
                System.out.print(aByte + " ");
            }
            System.out.println();
        }

        try {
            task5();
        } catch(IOException e) {
        }

        System.out.println(task6());

        System.out.println(Arrays.toString(task7()));

    }

    private static byte[] task1() {
        byte[] bytes = new byte[] {1,1,0,0,1,0,1,1,0,0};
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 0) {
                bytes[i] = 1;
            } else {
                bytes[i] = 0;
            }
        }
        return bytes;
    }

    private static short[] task2() {
        short[] shorts = new short[8];
        for (int i = 0; i < shorts.length; i++) {
            shorts[i] = (short) (i * 3);
        }
        return shorts;
    }

    private static short[] task3() {
        short[] shorts = new short[]{1,5,3,2,11,4,5,2,4,8,9,1};
        for (int i = 0; i < shorts.length; i++) {
            if (shorts[i] < 6) {
                shorts[i] *= 2;
            }
        }
        return shorts;
    }

    private static byte[][] task4() {
        byte[][] bytes = new byte[5][5];
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < bytes[i].length; j++) {
               if (i==j) {
                   bytes[i][j] = 1;
                   bytes[i][(bytes.length-1)-i] = 1;
                   break;
               }
            }
        }
        return bytes;
    }

    private static void task5() throws IOException {
        int[] ints = new int[5];
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < ints.length; i++) {
            ints[i] = Integer.parseInt(reader.readLine());
        }
        Arrays.sort(ints);
/*        интернетом не пользовался (я так понимаю, что ожидаемое решение - ввести 2 переменные min = Integer.MIN_VALUEM. и max = Integer.MAX_VALUEM, и сравнивать,
         в цикле, со всеми значениями массива, и перезаписывать по условию)*/
        System.out.println("min: " + ints[0]);
        System.out.println("max: " + ints[ints.length-1]);
    }

    private static boolean task6() {
       return checkBalance(new int[]{1, 1, 1, 2, 1});
    }

    private static boolean checkBalance(int[] ints) {
        int leftPart = 0;
        int rightPart = 0;
        for (int i = 0; i < ints.length-1; i++) {
            leftPart += ints[i];
            for (int j = i+1; j < ints.length; j++) {
                rightPart += ints[j];
            }
            if (leftPart == rightPart) {
                return true;
            }
            rightPart = 0;
        }
        return false;
    }

    private static int[] task7() {
        /*Не понял чем заполнять смещенные позиции, буду это делать 0, а те значения, котрые вышли за предел размерности массива - удалять*/
        return shiftArray(new int[]{1,2,3,4,5,6,7,8,9,10}, 0);
    }

    private static int[] shiftArray(int[] ints, int shiftTo) {
        if (shiftTo < 0) {
            for (int i = 0; i < ints.length; i++) {
                if ((i + shiftTo) >= 0 && (i + shiftTo) < ints.length) {
                    ints[i + shiftTo] = ints[i];
                    ints[i] = 0;
                }
            }
            return ints;
        }
        if (shiftTo > 0) {
            for (int i = ints.length-1; i >= 0; i--) {
                if ((i + shiftTo) >= 0 && (i + shiftTo) < ints.length) {
                    ints[i + shiftTo] = ints[i];
                    ints[i] = 0;
                }
            }
            return ints;
        }
        return ints;
    }
}
