package fibbonachiNumber;

import java.util.Scanner;

/*
Задача на программирование: последняя цифра большого числа Фибоначчи
Дано число 1≤n≤10^7, необходимо найти последнюю цифру n-го числа Фибоначчи.
Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их вычислении нужно быть аккуратным с переполнением.
В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только последняя цифра числа Фибоначчи:
если 0≤a,b≤9 — последние цифры чисел Fi и Fi+1 соответственно, то (a+b)mod10 — последняя цифра числа Fi+2.
Sample Input:
875577
Sample Output:
2
 */
public class Main {

    public static int fib(int n) {
        int f0 = 0, f1 = 1, fn = 1;
        for (int i = 2; i <= n; i++) {
            fn = (f1 + f0) % 10;
            f0 = f1;
            f1 = fn;
        }

        return fn;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.print(fib(s.nextInt()));
    }
}
