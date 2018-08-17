package maxWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Максимум в скользящем окне
Найти максимум в каждом окне размера m данного массива чисел
A[1 . . . n].
Вход. Массив чисел A[1 . . . n] и число 1 ≤ m ≤ n.
Выход. Максимум подмассива A[i . . . i + m − 1] для всех 1 ≤
i ≤ n − m + 1.
Sample Input 1:

3
2 1 5
1

Sample Output 1:

2 1 5

Sample Input 2:

8
2 7 3 1 5 2 6 2
4

Sample Output 2:

7 7 5 6 6
 */
public class Main {
    static Stack<Integer> in = new Stack<>();
    static Stack<Integer> out = new Stack<>();
    static Stack<Integer> max = new Stack<>();
    static int inMax = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());
        String[] line = reader.readLine().split(" ");
        int size = Integer.parseInt(reader.readLine());



        //добавили элементов по размеру окна
        for (int j = 0; j < size; j++) {
            int n = Integer.parseInt(line[j]);
            in.push(n);
            if (inMax < n) {
                inMax = n;
            }
        }

        //добавляем по одному и каждый раз находим максимум
        for (int i = size; i < count;) {
            if (!out.isEmpty()) {
                int n = Integer.parseInt(line[i]);
                out.pop();
                int print = max.pop();
                if (inMax > print) {
                    System.out.println(inMax);
                } else {
                    System.out.println(print);
                }
                in.push(n);
                if (inMax < n) {
                    inMax = n;
                }
                i++;
            } else {
                stackIntoOut(in.size());
            }
        }
        if (!max.isEmpty()) {
            int t = max.pop();
            if (t > inMax) {
                System.out.println(t);
            } else {
                System.out.println(inMax);
            }
        } else {
            System.out.println(inMax);
        }



    }

    private static void stackIntoOut(int size) {
        for (int i = 0; i < size; i++) {
            int n = in.pop();
            out.push(n);
            if (max.isEmpty()) {
                max.push(n);
            } else {
                int temp = max.peek();
                if (temp > n) {
                    max.push(temp);
                } else {
                    max.push(n);
                }
            }
        }
        inMax = 0;
    }

}
