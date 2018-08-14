package nod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Задача на программирование: наибольший общий делитель
По данным двум числам 1≤a,b≤2⋅10^9 найдите их наибольший общий делитель.
Sample Input 1:
18 35
Sample Output 1:
1
Sample Input 2:
14159572 63967072
Sample Output 2:
4
 */
public class Main {

    public static void main(String[] args) throws IOException {
        // put your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str = reader.readLine().split(" ");
        long a = Integer.parseInt(str[0]);
        long b = Integer.parseInt(str[1]);
        System.out.print(nod(a,b));
    }

    private static long nod(long x, long y) {

        if (x == 0)
            return y;
        if (y == 0)
            return x;
        if (x > y) {
            return nod(x%y, y);
        } else return nod(x,y%x);

    }
}
