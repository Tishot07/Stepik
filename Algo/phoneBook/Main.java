package phoneBook;

import java.io.*;
import java.util.*;

/*
Телефонная книга
Реализовать структуру данных, эффективно обрабатывающую запро-
сы вида add number name, del number и find number.
Вход. Последовательность запросов вида add number
name, del number и find number, где number — те-
лефонный номер, содержащий не более семи знаков,
а name — короткая строка.
Выход. Для каждого запроса find number выведите соот-
ветствующее имя или сообщите, что такой записи нет.
 Sample Input 1:

12
add 911 police
add 76213 Mom
add 17239 Bob
find 76213
find 910
find 911
del 910
del 911
find 911
find 76213
add 76213 daddy
find 76213

Sample Output 1:

Mom
not found
police
not found
Mom
daddy

Sample Input 2:

8
find 3839442
add 123456 me
add 0 granny
find 0
find 123456
del 0
del 0
find 0

Sample Output 2:

not found
granny
me
not found
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < count; i++) {
            String[] line = reader.readLine().split(" ");
            String command = line[0];

            switch(command) {
                case "add" :
                    int number = Integer.parseInt(line[1]);
                    String name = line[2];
                    map.put(number, name);
                    break;
                case "find" :
                    int number2 = Integer.parseInt(line[1]);
                    String res = map.get(number2);
                    if (res == null) {
                        System.out.println("not found");
                    } else {
                        System.out.println(res);
                    }

                    break;
                case "del" :
                    int number3 = Integer.parseInt(line[1]);
                    map.remove(number3);
                    break;
            }
        }
    }
}

/*
//
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int amountOfRequests = sc.nextInt();
            Map<Integer, String> telephBook = new HashMap<>();
            for (int i = 0; i < amountOfRequests; i++) {
                String request = sc.next();
                switch (request) {
                    case "add":
                        telephBook.put(sc.nextInt(), sc.next());
                        break;
                    case "del":
                        telephBook.remove(sc.nextInt());
                        break;
                    default:
                        Integer key = sc.nextInt();
                        System.out.println(telephBook.containsKey(key) ? telephBook.get(key) : "not found");
                }
            }
        }
    }
}
 */