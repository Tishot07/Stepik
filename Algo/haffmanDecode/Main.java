package haffmanDecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
Задача на программирование: декодирование Хаффмана

Восстановите строку по её коду и беспрефиксному коду символов.
В первой строке входного файла заданы два целых числа k и l через пробел — количество различных букв,
встречающихся в строке, и размер получившейся закодированной строки, соответственно.
В следующих k строках записаны коды букв в формате "letter: code".
Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке.
В качестве букв могут встречаться лишь строчные буквы латинского алфавита; каждая из этих букв встречается в строке хотя бы один раз.
Наконец, в последней строке записана закодированная строка. Исходная строка и коды всех букв непусты.
Заданный код таков, что закодированная строка имеет минимальный возможный размер.
В первой строке выходного файла выведите строку s. Она должна состоять из строчных букв латинского алфавита.
Гарантируется, что длина правильного ответа не превосходит 10^4 символов.

Sample Input 1:

1 1
a: 0
0

Sample Output 1:

a

Sample Input 2:

4 14
a: 0
b: 10
c: 110
d: 111
01001100100111

Sample Output 2:

abacabad
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str = reader.readLine().split(" ");
        int countSymbol = Integer.parseInt(str[0]);

        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < countSymbol; i++) {
            String[] temp = reader.readLine().split(" ");
            char ch = temp[0].charAt(0);
            String code = temp[1];
            map.put(ch, code);
        }

        String LineCode = reader.readLine();

        StringBuffer result = new StringBuffer();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < LineCode.length(); i++) {

            temp.append(LineCode.charAt(i));
            for (Map.Entry<Character, String> entry : map.entrySet()) {
                if (entry.getValue().startsWith(temp.toString())) {
                    if (entry.getValue().equals(temp.toString())) {
                        result.append(entry.getKey());
                        temp = new StringBuilder();
                        break;
                    }
                }
            }
        }

        System.out.print(result.toString());
    }
}
