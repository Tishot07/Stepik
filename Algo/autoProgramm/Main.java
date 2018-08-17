package autoProgramm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
При автоматическом анализе программ возникает такая задача.
Система равенств и неравенств
Проверить, можно ли присвоить переменным целые значения, чтобы
выполнить заданные равенства вида x i = x j и неравенства вида x p 6 = x q .
Вход. Число переменных n, а также список равенств вида
x i = x j и неравенства вида x p 6 = x q .
Выход. Проверить, выполнима ли данная система.
Sample Input 1:

4 6 0
1 2
1 3
1 4
2 3
2 4
3 4

Sample Output 1:

1

Sample Input 2:

4 6 1
1 2
1 3
1 4
2 3
2 4
3 4
1 2

Sample Output 2:

0

Sample Input 3:

4 0 6
1 2
1 3
1 4
2 3
2 4
3 4

Sample Output 3:

1
 */
public class Main {

    public static int[] parent;


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = reader.readLine().split(" ");
        //длинна массива
        int n = Integer.parseInt(line1[0]);
        //равные
        int d = Integer.parseInt(line1[1]);
        //не равные
        int e = Integer.parseInt(line1[2]);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int k = 0; k < d; k++) {
            String[] str = reader.readLine().split(" ");
            int i = Integer.parseInt(str[0]);
            int j = Integer.parseInt(str[1]);
            Union(i-1, j-1);

        }
        int zero = 0;
        for (int k = 0; k < e; k++) {
            String[] str = reader.readLine().split(" ");
            int i = Integer.parseInt(str[0]);
            int j = Integer.parseInt(str[1]);
            if (Find(i-1) == Find(j-1)) {
                zero++;
            }
        }
        if (zero == 0) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }


    }

    private static void Union(int d, int s) {
        int dId = Find(d);
        int sId = Find(s);

        if (dId == sId)
            return;
        parent[dId] = sId;

    }

    private static int Find(int i) {
        if (i != parent[i]) {
            parent[i] = Find(parent[i]);
        }
        return parent[i];
    }


}
