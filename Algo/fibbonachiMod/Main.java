package fibbonachiMod;

/*
Задача на программирование повышенной сложности: огромное число Фибоначчи по модулю
Даны целые числа 1≤n≤10^18 и 2≤m≤10^5, необходимо найти остаток от деления n-го числа Фибоначчи на m.
Sample Input:
10 2
Sample Output:
1

Решено с помощью периода Пизано
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
class Main {
    public static void main(String[] args) {
        // put your code here
        BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
        long n = 0;
        int m = 0;
        try {
            String in = bis.readLine();
            String[] temp = in.split(" ");
            n = Long.parseLong(temp[0]);
            m = Integer.parseInt(temp[1]);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.print(getFibonacciRest(n,m));
    }

    private static ArrayList<Long> getSequencePeriod(long m){
        ArrayList<Long> s = new ArrayList();
        s.add((long)0);
        s.add((long)1);
        for(int i = 2; i < m * 6; i++){
            s.add((s.get(i - 1) + s.get(i - 2)) % m);
            if(s.get(i) == 1 && s.get(i-1) == 0){
                break;
            }
        }
        return s;
    }

    private static long getFibonacciRest(long n, long m){
        ArrayList<Long> s = getSequencePeriod(m);
        long period = s.size() - 2; // находим период Пизано
        int val = (int)(n % period);
        return s.get(val);
    }

}

