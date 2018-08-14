package sortLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
Задача на программирование: сортировка подсчётом

Первая строка содержит число 1≤n≤10^4, вторая — n натуральных чисел, не превышающих 10.
Выведите упорядоченную по неубыванию последовательность этих чисел.

Sample Input:

5
2 3 9 2 9

Sample Output:

2 2 3 9 9
 */
public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());
        String[] strArray = reader.readLine().split(" ");

        int[] array = new int[count];
        for (int i = 0; i < count; i++) {
            array[i] = Integer.parseInt(strArray[i]);
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int element : array)
        {
            if (element < min)
            {
                min = element;
            }
            if (element > max)
            {
                max = element;
            }
        }

        int[] sortArray = new int[max - min + 1];
        for (int element : array)
        {
            sortArray[element - min]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < sortArray.length; i++)
        {
            for (int j = sortArray[i]; j > 0; j--)
            {
                array[arrayIndex++] = i + min;
            }
        }

        for (int i = 0; i < count; i++) {
            System.out.print(array[i] + " ");
        }
    }
}

/*
//другой вариант
import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            Arrays.stream(countSort(a, 10)).forEach(x -> System.out.printf("%d ", x));
        }
    }

    private static int[] countSort(int[] a, int m) {
        int[] b = new int[m + 1];
        Arrays.stream(a).forEach(x -> b[x] ++);
        b[0] --;
        for (int i = 1; i < m + 1; i++) {
            b[i] = b[i] + b[i - 1];
        }
        int[] a1 = new int[a.length];
        for (int j = a.length - 1; j >= 0; j--) {
            a1[b[a[j]]] = a[j];
            b[a[j]] = b[a[j]] - 1;
        }
        return a1;
    }
}
 */