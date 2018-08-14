package backpack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Задача на программирование: рюкзак

﻿Первая строка входа содержит целые числа 1≤W≤10^4 и 1≤n≤300 — вместимость рюкзака и число золотых слитков.
Следующая строка содержит n целых чисел 0≤w1,…,wn≤10^5, задающих веса слитков.
Найдите максимальный вес золота, который можно унести в рюкзаке.

Sample Input:

10 3
1 4 8

Sample Output:

9
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] A = reader.readLine().split(" ");
        int W = Integer.parseInt(A[0]);			//размер рюкзака
        int n = Integer.parseInt(A[1]); 		//число вещей
        String[] B = reader.readLine().split(" ");
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = Integer.parseInt(B[i]);		//задаем вес каждой вещи
        }

        int[][] D = new int[W + 1][n + 1];
        for (int i = 0; i < W; i++) {
            D[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            D[0][j] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                D[j][i] = D[j][i - 1];
                if (w[i-1] <= j) {
                    D[j][i] = Integer.max(D[j][i], D[j - w[i-1]][i - 1] + w[i-1]);
                }
            }
        }

        System.out.println(D[W][n]);
    }

}

