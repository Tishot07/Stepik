package summands;
import java.io.*;
import java.util.*;

/*
Задача на программирование: различные слагаемые

По данному числу 1≤n≤10^9 найдите максимальное число k, для которого n можно представить как сумму k различных натуральных слагаемых. Выведите в первой строке число k, во второй — k слагаемых.
Sample Input 1:

4

Sample Output 1:

2
1 3

Sample Input 2:

6

Sample Output 2:

3
1 2 3
 */
class Main {
    public static void main(String[] args) throws IOException {
        // put your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        int num = Integer.parseInt(str);

        List<Integer> list = new ArrayList<>();

        int j = 1;
        while (num >= 0) {
            if ((num - j) > j) {
                num -= j;
                list.add(j);
                j++;
            } else {
                list.add(num);
                break;
            }
        }
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }
}
