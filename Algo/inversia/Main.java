package inversia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Задача на программирование: число инверсий

﻿Первая строка содержит число 1≤n≤10^5, вторая — массив A[1…n], содержащий натуральные числа, не превосходящие 10^9.
Необходимо посчитать число пар индексов 1≤i<j≤n, для которых A[i]>A[j].
(Такая пара элементов называется инверсией массива. Количество инверсий в массиве является
в некотором смысле его мерой неупорядоченности: например, в упорядоченном по неубыванию массиве инверсий нет вообще,
а в массиве, упорядоченном по убыванию, инверсию образуют каждые два элемента.)

Sample Input:

5
2 3 9 2 9

Sample Output:

2
 */
public class Main {
    static long res = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());

        long[] array = new long[count];

        String[] strArray = reader.readLine().split(" ");
        for (int i = 0; i < count; i++) {
            array[i] = Long.parseLong(strArray[i]);
        }


        array = recMerge(array);
			/*
			for (int i = 0; i < count; i++) {
				System.out.print(array[i] + " ");
			}
			*/
        //System.out.println();
        System.out.println(res);


    }

    private static long[] recMerge(long[] ar) {
        int n = ar.length;
        int mid = n / 2;
        if (n == 1) {
            return ar;
        }
        long[] a = new long[mid];
        long[] b = new long[n - mid];
        System.arraycopy(ar, 0, a, 0, mid);
        System.arraycopy(ar, mid, b, 0, n-mid);
        a = recMerge(a);
        b = recMerge(b);
        return merge(a, b);
    }

    private static long[] merge(long[] a, long[] b) {
        int i = 0;
        int j = 0;
        long[] result = new long[a.length + b.length];
        for (int k = 0; k < result.length; k++) {
            if (j == b.length || (i < a.length && a[i] <= b[j])) {
                result[k] = a[i++];
            } else {
                result[k] = b[j++];
                res += a.length - i;
            }
        }
        return result;
    }
}

