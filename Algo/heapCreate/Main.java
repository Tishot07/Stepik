package heapCreate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Построение кучи
Переставить элементы заданного массива чисел так, чтобы он удовле-
творял свойству мин-кучи.
Вход. Массив чисел A[0 . . . n − 1].
Выход. Переставить элементы массива так, чтобы выпол-
нялись неравенства A[i] ≤ A[2i + 1] и A[i] ≤ A[2i + 2] для
всех i.
Sample Input 1:

6
0 1 2 3 4 5

Sample Output 1:

0

Sample Input 2:

6
7 6 5 4 3 2

Sample Output 2:

4
2 5
1 4
0 2
2 5
 */
public class Main {

    static List<String> result;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(reader.readLine());
        String[] str = reader.readLine().split(" ");
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = Integer.parseInt(str[i]);
        }

        result = new ArrayList<>();

        for (int i = (size - 1); i >= 0; i--) {
            int p = (i - 1)/2;
            Down(array, p);
        }

        System.out.println(result.size());
        for(int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

    }

    private static void Down(int[] H, int i) {
        int leftChild;
        int rightChild;
        int min;

        for (; ; )
        {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            min = i;

            if (leftChild < H.length && H[leftChild] < H[min])
            {
                min = leftChild;
            }

            if (rightChild < H.length && H[rightChild] < H[min])
            {
                min = rightChild;
            }

            if (min == i)
            {
                break;
            }
            result.add(i + " " + min);
            //System.out.println(i + " " + min);
            int temp = H[i];
            H[i] = H[min];
            H[min] = temp;
            i = min;
        }
    }

    private static void swap(int[] H, int a, int b) {
        int temp = H[a];
        H[a] = H[b];
        H[b] = temp;
    }

}
