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

/*
//Другое решение
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Collections;

class Main {
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        LinkedList<Integer> queue_push = new LinkedList<Integer>();
        LinkedList<Integer> queue_pop = new LinkedList<Integer>();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < m; i++) {
            if (max < arr[i]) max = arr[i];
            queue_push.addFirst(arr[i]);
        }

        int max_push=max;
        for (int i = m; i < n; i++) {
            if (queue_pop.isEmpty()) {
                max = Integer.MIN_VALUE;
                while (!queue_push.isEmpty()) {
                    int temp_val = queue_push.removeFirst();
                    max = Math.max(temp_val, max);
                    queue_pop.addFirst(max);
                }
                max_push = Integer.MIN_VALUE;
            }

            max = queue_pop.removeFirst();
            queue_push.addFirst(arr[i]);
            max = Math.max(max,max_push);

            System.out.print(max + " ");
            max_push = Math.max(max_push, arr[i]);
        }

        System.out.print((queue_pop.isEmpty())?max_push:Math.max(max_push,queue_pop.getFirst()));
  }
}


//еще одно решение
import java.util.*;
public class Main {

    public static Stack<Integer> stackIn = new Stack<>();
    public static Stack<Integer> stackOut = new Stack<>();
    public static Stack<Integer> stackMax = new Stack<>();
    public static int m;
    public static int maxIn = Integer.MIN_VALUE;

    public static void filling() {
        for (int i = 0; i < m; i++) {
            stackOut.push(stackIn.pop());
            stackMax.push(stackMax.isEmpty() ? stackOut.peek() : Math.max(stackOut.peek(), stackMax.peek()));
        }
        stackIn = new Stack<>();
        maxIn = Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = sc.nextInt();
        }
        m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            maxIn = Math.max(maxIn, ar[i]);
            stackIn.push(ar[i]);
        }
        for (int i = m; i < n; i++) {
            if (stackOut.isEmpty()) {
                filling();
            }
            System.out.print(Integer.toString(Math.max(stackMax.pop(), maxIn)).concat(" "));
            stackOut.pop();
            maxIn = Math.max(ar[i], maxIn);
            stackIn.push(ar[i]);
        }
        System.out.print(Math.max(maxIn, stackMax.isEmpty() ? maxIn : stackMax.peek()));
    }
}

//без доп памяти
import java.io.*;
import java.util.*;
public class Main {

    public static class MyReader{
        private BufferedReader reader = null;
        private StringTokenizer tokenizer = null;
        MyReader(Reader r) throws IOException{
            reader = new BufferedReader(r);
        }
        public int nextInt() throws IOException {
            return Integer.parseInt(nextToken());
        }
        public String nextToken() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }
    }

    public static int[] findMax(int start, int m, int[] ar) {
        int maxVal = -1;
        int maxInd = -1;
        for (int i = start; i < start + m; i++) {
            if (ar[i] > maxVal) {
                maxVal = ar[i];
                maxInd = i;
            }
        }
        return new int[] {maxVal, maxInd};
    }

    public static void main(String[] args) throws IOException {
        MyReader sc = new MyReader(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i < n; i++) {
            ar[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        int maxVal = -1;
        int ind = -1;
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n - m + 1; i++) {
            if (i > ind) {
                int[] a = findMax(i, m, ar);
                maxVal = a[0];
                ind = a[1];
            } else {
                if (maxVal <= ar[i + m - 1]) {
                    maxVal = ar[i + m - 1];
                    ind = i + m - 1;
                }
            }
            ans.append(Integer.toString(maxVal).concat(" "));
        }
        System.out.println(ans.toString());
    }
}
 */