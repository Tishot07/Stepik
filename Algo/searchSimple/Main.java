package searchSimple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Поиск образца в тексте
Найти все вхождения строки Pattern в строку Text.
Вход. Строки Pattern и Text.
Выход. Все индексы i строки Text, начиная с которых стро-
ка Pattern входит в Text:
Text[i..i + |Pattern| − 1] = Pattern.
 Sample Input 1:

aba
abacaba

Sample Output 1:

0 4

Sample Input 2:

Test
testTesttesT

Sample Output 2:

4

Sample Input 3:

aaaaa
baaaaaaa

Sample Output 3:

1 2 3
 */
public class Main {


    private static  char[] Text;


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String subStr = reader.readLine();
        String str = reader.readLine();

        RabinKarp(str, subStr);

    }

    private static void RabinKarp(String s, String sub) {

        int n = s.length();
        int m = sub.length();

        int hSub = hash(sub);
        int hS = hash(s.substring(0, m));

        Text = s.toCharArray();
        char[] subArray = sub.toCharArray();

        for (int i = 0; i < (n - m + 1); i++) {
            if (hS == hSub) {
                if (Text[i] == subArray[0]) {
                    if (s.substring(i, i+m).equals(sub)) {
                        System.out.print(i + " ");

                    }
                }

            }
            if (i >= n - m)
                return;
            hS = reHash(hS, i, m);

        }

    }

    private static int reHash(int h, int i, int m) {
        return h - (int)Text[i] + (int) Text[i + m];
    }

    private static int hash(String str) {
        char[] ch = str.toCharArray();
        int n = ch.length;
        int result = 0;
        for (char c : ch)
            result += (int)c;
        return result;
    }


}

/*
//
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String pattern = sc.next();
            String text = sc.next();
            find(text, pattern);
        }
    }

    private static int hash(String s) {
        return s.chars().reduce(Integer::sum).getAsInt();
    }

    private static void find(String text, String pattern) {
        int hashPattern = hash(pattern);
        int hashPartOfText = hash(text.substring(0, pattern.length()));
        char first = pattern.charAt(0);

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            if (hashPartOfText == hashPattern && text.charAt(i) == first) {
                if (text.substring(i, pattern.length() + i).equals(pattern)) {
                    System.out.println(i);
                }
            }

            if (i != text.length() - pattern.length()) {
                hashPartOfText = hashPartOfText - (int)text.charAt(i) + (int)(text.charAt(i + pattern.length()));
            }
        }
    }
}
 */