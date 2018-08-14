package parallel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
Параллельная обработка
По данным n процессорам и m задач определите, для каждой из задач,
каким процессором она будет обработана.
Вход. Число процессоров n и последовательность чисел
t 0 , . . . , t m−1 , где t i — время, необходимое на обработку i-й
задачи.
Выход. Для каждой задачи определите, какой процессор
и в какое время начнёт её обрабатывать, предполагая, что
каждая задача поступает на обработку первому освободив-
шемуся процессору.
 Sample Input:

2 5
1 2 3 4 5

Sample Output:

0 0
1 0
0 1
1 2
0 4
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = reader.readLine().split(" ");
        int num = Integer.parseInt(line1[0]);				//количество процессоров
        int count = Integer.parseInt(line1[1]);				//количество задач
        String[] line2 = reader.readLine().split(" ");

        PriorityQueue<Processor> q = new PriorityQueue<>(num);
        int n = 0;								//номер процессора
        for (int i = 0; i < count; i++) {
            int t = Integer.parseInt(line2[i]);		//время на задачу
            if (n < num) {
                System.out.println(n + " " + 0);
                Processor p = new Processor(t, n);
                q.add(p);
                n++;
            } else {
                Processor p = q.poll();
                System.out.println(p.number + " " + p.time);
                p.setTime(t);
                q.add(p);
            }
        }


    }

    private static class Processor implements Comparable<Processor> {
        long time;
        int number;

        Processor(long time, int number) {
            this.time = time;
            this.number = number;
        }

        void setTime(long time) {
            this.time += time;
        }

        @Override
        public int compareTo(Processor o) {
            // TODO Auto-generated method stub
            if (this.time < o.time)
                return -1;
            if (this.time == o.time) {
                if (this.number < o.number)
                    return -1;
                else
                    return 1;
            }
            if (this.time > o.time)
                return 1;
            return 0;
        }
    }

}

/*
//С лямбдами
import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        PriorityQueue<long[]> procs = new PriorityQueue<>((x,y)-> x[1]==y[1] ? (int)(x[0]-y[0]) : (int)(x[1]-y[1]));
        for (int i = 0; i < n; i++) {
            procs.add(new long[] {i,0});
        }
        for (int i = 0; i < m; i++) {
            long[] p = procs.poll();
            procs.offer(new long[] {p[0], p[1] + sc.nextInt()});
            System.out.println(Long.toString(p[0]).concat(" ").concat(Long.toString(p[1])));
        }
    }
}
 */