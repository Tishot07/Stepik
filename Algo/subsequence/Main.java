package subsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Задача на программирование: наибольшая последовательнократная подпоследовательность

Дано целое число 1≤n≤10^3 и массив A[1…n] натуральных чисел, не превосходящих 2*10^9.
Выведите максимальное 1≤k≤n, для которого найдётся подпоследовательность 1≤i1<i2<…<ik≤n длины k,
в которой каждый элемент делится на предыдущий (формально: для  всех 1≤j<k, A[ij]|A[ij+1]).

Sample Input:

4
3 6 7 12

Sample Output:

3
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());
        String[] str = reader.readLine().split(" ");

        int[] array = new int[count];
        for (int i = 0; i < count; i++ ) {
            array[i] = Integer.parseInt(str[i]);
        }

        int[] D = new int[count];
        for (int i = 0; i < count; i++) {
            D[i] = 1;
            for (int j = 0; j < i; j++) {
                if ((array[i] % array[j] == 0) && (D[j] + 1) > D[i]) {

                    D[i] = D[j] + 1;

                }
            }
        }

        int ans = 0;
        for (int i = 0; i < count; i++) {
            if (ans < D[i]) {
                ans = D[i];
            }
        }

        System.out.println(ans);

    }
}

