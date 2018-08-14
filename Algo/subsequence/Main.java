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

/*
//другой вариант

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        new Main().go();
    }

    private void go() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        LIS(nums);


    }

    private void LIS(int[] nums) {
        int[] D = new int[nums.length];
        int[] prev = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            D[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if ((nums[i] % nums[j] == 0) && (D[j] + 1 > D[i])) {
                    D[i] = D[j] + 1;
                    prev[i] = j;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, D[i]);
        }
        System.out.println(ans);
    }

}

 */
