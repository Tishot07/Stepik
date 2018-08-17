package lineDot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Задача на программирование: покрыть отрезки точками
По данным n отрезкам необходимо найти множество точек минимального размера, для которого каждый из отрезков содержит хотя бы одну из точек.
В первой строке дано число 1≤n≤100 отрезков. Каждая из последующих n строк содержит по два числа 0≤l≤r≤109, задающих начало и конец отрезка.
Выведите оптимальное число m точек и сами m точек. Если таких множеств точек несколько, выведите любое из них.

Sample Input 1:
3
1 3
2 5
3 6
Sample Output 1:
1
3
Sample Input 2:
4
4 7
1 3
2 5
5 6
Sample Output 2:
2
3 6
 */
public class Main {

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;		//start
            this.y = y;		//end
        }

        @Override
        public String toString() {
            return "[" + x + "," + y + "]";
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Point> list = new ArrayList<>();


        int numPoint = Integer.parseInt(reader.readLine());

        for (int i = 0; i < numPoint; i++) {
            String[] str = reader.readLine().split(" ");
            list.add(new Point(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }

        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.y > o2.y ? +1 : o1.y < o2.y ? -1 : 0;
            }
        });


        int index = 0;
        int line = 0;		//count points for line
        List<Integer> dotList = new ArrayList<>();

        while (!list.isEmpty() ) {
            //System.out.println("First: ");
            Point first = list.get(index);
            //System.out.println(first);

            //int sizeList = list.size() - index;
            for (int i = 0; i < list.size(); i++) {
                Point next = list.get(i);
                //System.out.println("next: " + next);
                if (next.x <= first.y && first.y <= next.y) {
                    //System.out.println("Delete: " + next);
                    list.remove(i);
                    index++; i--;
                }
            }
            line++;
            dotList.add(first.y);
            index = 0;
        }
        System.out.println(line);
        for (int i = 0; i < dotList.size(); i++)
            System.out.print(dotList.get(i) + " ");

    }
}

