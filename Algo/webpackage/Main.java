package webpackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.*;

/*
Обработка сетевых пакетов
Реализовать обработчик сетевых пакетов.
Вход. Размер буфера size и число пакетов n, а так-
же две последовательности arrival 1 , . . . , arrival n и
duration 1 , . . . , duration n , обозначающих время поступ-
ления и длительность обработки n пакетов.
Выход. Для каждого из данных n пакетов необходимо
вывести время начала его обработки или −1, если пакет
не был обработан (это происходит в случае, когда пакет
поступает в момент, когда в буфере компьютера уже
находится size пакетов).
Sample Input 1:
1 0
Sample Output 1:
Sample Input 2:
1 1
0 0
Sample Output 2:
0
Sample Input 3:
1 1
0 1
Sample Output 3:
0
 */
public class Main {

    static int SysTime = 0;


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str1 = reader.readLine().split(" ");
        int size = Integer.parseInt(str1[0]);
        int count = Integer.parseInt(str1[1]);

        //храним, когда закончит обрабатываться пакет
        Deque<Integer> queue = new LinkedBlockingDeque<>(size);
        int printTime = 0;

        for (int i = 0; i < count; i++) {

            String[] str2 = reader.readLine().split(" ");
            int time = Integer.parseInt(str2[0]);
            int duration = Integer.parseInt(str2[1]);

            //убираем пакеты, у которых время окончания на момент прихода пакета закончилось
            while (!queue.isEmpty() && queue.peek() <= time) {
                queue.poll();
            }

            if (queue.isEmpty()) {
                //если очереь пуста - время когда пришел пакет - сразу на обработку
                System.out.println(time);
                //printTime = time;
                //Вычисляем время процессора
                //если время когда пришел пакет больше его времени обработки

                if (time > duration) {
                    SysTime = time + duration;
                } else {
                    SysTime = duration;
                }

                //окончание работы (тк это первй пакет, время его работы и будет временем кончания его работы)
                queue.add(time + duration);

            } else if (queue.size() < size) {
                //время начала работы пришедшего пакета = когда начала обрабатываться предыдущий пакет + когда она закончит орабатываться
                //printTime = queue.getLast();
                System.out.println(queue.getLast());
                //Вычисляем время процессора
                SysTime += duration;
                queue.add(duration + queue.getLast());

            } else {
                System.out.println(-1);
            }




        }
    }

}

