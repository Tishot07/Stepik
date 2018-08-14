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

/*
//Другое решение
import java.util.Scanner;
import java.util.ArrayList;

class Main {
  public static void main(String[] args) {
    // put your code here
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        int m = sc.nextInt();
        System.out.println(fibPizano(n, m));
  }

    public static ArrayList<Integer> findPisano(int m)
    {

        ArrayList<Integer> pisanoList = new ArrayList<Integer>();
        pisanoList.add(0);
        pisanoList.add(1);

        int a = 0;
        int b = 1;
        int c;

        for (long i = 2; i < m*6; i++)
        {
            c = (a + b) % m;
            a = b;
            b = c;
            pisanoList.add(c);

            if (((pisanoList.get(pisanoList.size() - 1)) == 1) && ((pisanoList.get(pisanoList.size() - 2)) == 0))
                break;
        }

        return pisanoList;

    }

    public static int fibPizano(long n, int m)
    {
        ArrayList<Integer> pisanoList = findPisano(m);
        int period = pisanoList.size() - 2;
        return pisanoList.get((int) (n % period));
    }

}

//еще одно
class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scaner = new Scanner(System.in);
        long n = scaner.nextLong();
        // max int == 2147483647 > 100000.0
        int m = scaner.nextInt();
        System.out.println(modfib(n, m));
    }

    private static int modfib(long n, int m) {
        if (n >= 1 && m >= 2 && m <= Math.pow(10, 5) && n <= Math.pow(10, 18)) {
            int[] mas = new int[m * 6];
            mas[0] = 0;
            mas[1] = 1;
            for (int i = 2; i <= mas.length; i++) {
                if ((mas[i] = ((mas[i - 1] + mas[i - 2]) % m)) == 1 && mas[i - 1] == 0) {
                    return mas[(int) (n % (i - 1))];
                }
            }
        }
        return 0;
    }
}
 */