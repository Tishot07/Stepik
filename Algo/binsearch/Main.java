package binsearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Задача на программирование: двоичный поиск

﻿В первой строке даны целое число 1≤n≤10^5 и массив A[1…n] из n различных натуральных чисел, не превышающих 10^9,
в порядке возрастания, во второй — целое число 1≤k≤105 и k натуральных чисел b1,…,bk, не превышающих 10^9.
Для каждого i от 1 до k необходимо вывести индекс 1≤j≤n, для которого A[j]=bi, или −1, если такого j нет.

Sample Input:

5 1 5 8 12 13
5 8 1 23 1 11

Sample Output:

3 1 -1 1 -1
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] arrays = reader.readLine().split(" ");			//массив
        String[] indexes = reader.readLine().split(" ");		//найти эти числа в массиве

        int countArray = Integer.parseInt(arrays[0]);		//количество элементов в массиве
        long[] array = new long[countArray];
        for (int i = 0; i < countArray; i++) {
            array[i] = Long.parseLong(arrays[i+1]);
        }

        int countIndex = Integer.parseInt(indexes[0]);
        Arrays.sort(array);

        List<Integer> result = new ArrayList<>();
        //int l = 0;
        //int n = countArray;
        for (int i = 0; i < countIndex; i++) {
            long k = Long.parseLong(indexes[i+1]);
            result.add(find(array, k, 0, countArray -1));
        }

        for (int j : result) {
            System.out.print(j + " ");
        }
    }

    private static int find(long[] array, long key, int start, int end) {
        while (true) {
            int m = (start + end) / 2;
            if (array[m] == key) {
                return m + 1;
            } else if (start > end) {
                return -1;
            } else {
                if (array[m] > key) {
                    end = m - 1;
                } else {
                    start = m + 1;
                }
            }
        }
    }

}
