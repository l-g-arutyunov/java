package ru.geekbrains.lesson1;


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
/*        интернетом не пользовался (я так понимаю, что ожидаемое решение - ввести 2 переменные min = Integer.MAX_VALUEM. и max = Integer.MIN_VALUEM, и сравнивать,
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
        return shiftArray(new int[]{1,2,3,4,5,6,7,8,9,10}, 3);
    }

    private static int[] shiftArray(int[] ints, int shiftTo) {
        for (int i = 0; i < Math.abs(shiftTo); i++) {
        if (shiftTo > 0 && ints.length > 1) {
                int temp = ints[0];
                for (int j = ints.length-1; j > 0; j--) {
                    if (j+1 != ints.length) {
                        ints[j + 1] = ints[j];
                    } else {
                        ints[0] = ints[j];
                    }
                }
                ints[1] = temp;
            }
            if (shiftTo < 0 && ints.length > 1) {
                int temp = ints[ints.length-1];
                for (int j = 0; j < ints.length; j++) {
                    if (j != 0) {
                        ints[j - 1] = ints[j];
                    } else {
                        ints[ints.length-1] = ints[j];
                    }
                }
                ints[ints.length-2] = temp;
            }
        }
        return ints;
    }
}
