package distance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Задача на программирование: расстояние редактирования

Вычислите расстояние редактирования двух данных непустых строк длины не более 10^2, содержащих строчные буквы латинского алфавита.

Sample Input 1:

ab
ab

Sample Output 1:

0

Sample Input 2:

short
ports

Sample Output 2:

3
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String A = reader.readLine();
        String B = reader.readLine();

        System.out.println(editdist(A, B));
    }

    private static int editdist(String S1, String S2) {
        int m = S1.length(), n = S2.length();
        int[] D1;
        int[] D2 = new int[n + 1];

        for(int i = 0; i <= n; i ++)
            D2[i] = i;

        for(int i = 1; i <= m; i ++) {
            D1 = D2;
            D2 = new int[n + 1];
            for(int j = 0; j <= n; j ++) {
                if(j == 0) D2[j] = i;
                else {
                    int cost = (S1.charAt(i - 1) != S2.charAt(j - 1)) ? 1 : 0;
                    if(D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
                        D2[j] = D2[j - 1] + 1;
                    else if(D1[j] < D1[j - 1] + cost)
                        D2[j] = D1[j] + 1;
                    else
                        D2[j] = D1[j - 1] + cost;
                }
            }
        }
        return D2[n];
    }

}

/*
//Другой вариант
import java.util.Scanner;
import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(editDistBU(sc.nextLine().chars().toArray(), sc.nextLine().chars().toArray()));
        }
    }

    private static int editDistBU(int[] b, int[] a) {
        int[][] d = new int[a.length + 1][b.length + 1];
        IntStream.rangeClosed(0, a.length).forEach(i -> d[i][0] = i);
        IntStream.rangeClosed(0, b.length).forEach(j -> d[0][j] = j);
        IntStream.range(0, a.length).forEach(i -> IntStream.range(0, b.length).forEach(j -> {
            int c = a[i] == b[j] ? 0 : 1;
            d[i + 1][j + 1] = min(d[i][j + 1] + 1, d[i + 1][j] + 1, d[i][j] + c);
        }));
        return d[a.length][b.length];
    }

    private static int min(int a, int b, int c) {
        return IntStream.of(a, b, c).min().orElse(Integer.MAX_VALUE);
    }
}
 */