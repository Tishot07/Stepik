package fibbonachi;

/*
Задача на программирование: небольшое число Фибоначчи
Дано целое число 1≤n≤40, необходимо вычислить n-е число Фибоначчи (напомним, что F0=0, F1=1 и Fn=Fn−1+Fn−2 при n≥2).
 */

import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long F1 = 0, F2 = 1, fib = 0;
        for (int i = 1; i <= n; i++){
            fib = F1+F2;
            F2 = F1;
            F1 = fib;
        }
        System.out.println(fib);
    }
}
