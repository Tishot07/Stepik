package stairs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Задача на программирование: лестница

Даны число 1≤n≤10^2 ступенек лестницы и целые числа −10^4≤a1,…,an≤10^4, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на одну или две ступеньки.

Sample Input 1:

2
1 2

Sample Output 1:

3

Sample Input 2:

2
2 -1

Sample Output 2:

1

Sample Input 3:

3
-1 2 1

Sample Output 3:

3
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());
        String[] str = reader.readLine().split(" ");
        int[] array = new int[count + 1];
        array[0] = 0;
        for (int i = 0; i < count; i++) {
            array[i+1] = Integer.parseInt(str[i]);
        }
        List<Integer> result = new ArrayList<>();
        result.add(0);
        result.add(array[1]);
        for (int i = 2; i <= count; i++) {
            result.add(Integer.max(result.get(i-1) + array[i], result.get(i-2) + array[i]));
        }


        System.out.println(result.get(count));
    }
}

/*
//с помощью лямбд
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;


class Main {
  public static void main(String[] args) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            reader.readLine();
            int[] values = new int[3];
            Arrays.stream(reader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .forEach(
                            value -> {
                                values[2] = value + Math.max(values[0], values[1]);
                                values[0] = values[1];
                                values[1] = values[2];
                            }
                    );
            System.out.println(values[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
  }
}
 */