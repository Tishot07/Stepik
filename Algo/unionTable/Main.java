package unionTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
4.1
Объединение таблиц
Ваша цель в данной задаче — реализовать симуляцию объединения
таблиц в базе данных.
В базе данных есть n таблиц, пронумерованных от 1 до n, над од-
ним и тем же множеством столбцов (атрибутов). Каждая таблица со-
держит либо реальные записи в таблице, либо символьную ссылку на
другую таблицу. Изначально все таблицы содержат реальные записи,
и i-я таблица содержит r i записей. Ваша цель — обработать m запро-
сов типа (destination i , source i ):
1. Рассмотрим таблицу с номером destination i . Пройдясь по цепоч-
ке символьных ссылок, найдём номер реальной таблицы, на ко-
торую ссылается эта таблица:
пока таблица destination i содержит символическую ссылку:
destination i ← symlink(destination i )
2. Сделаем то же самое с таблицей source i .
3. Теперь таблицы destination i и source i содержат реальные запи-
си. Если destination i 6 = source i , скопируем все записи из таблицы
source i в таблицу destination i , очистим таблицу source i и пропи-
шем в неё символическую ссылку на таблицу destination i .
4. Выведем максимальный размер среди всех n таблиц. Размером
таблицы называется число строк в ней. Если таблица содержит
символическую ссылку, считаем её размер равным нулю.
Формат входа. Первая строка содержит числа n и m — число таблиц
и число запросов, соответственно. Вторая строка содержит n це-
лых чисел r 1 , . . . , r n — размеры таблиц. Каждая из последующих
m строк содержит два номера таблиц destination i и source i , кото-
рые необходимо объединить.
Формат выхода. Для каждого из m запросов выведите максималь-
ный размер таблицы после соответствующего объединения.
Sample Input:

5 5
1 1 1 1 1
3 5
2 4
1 4
5 4
5 3

Sample Output:

2
2
3
5
5
 */
public class Main {

    public static List<Table> parent;
    public static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = reader.readLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);

        String[] value = reader.readLine().split(" ");
        parent = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            parent.add(new Table(Integer.parseInt(value[i]), i));
        }
        result = Collections.max(parent).columns;
        for (int i = 0; i < m; i++) {
            String[] str = reader.readLine().split(" ");
            int destination = Integer.parseInt(str[0]);
            int source = Integer.parseInt(str[1]);
            Union(destination, source);
            //System.out.println(Collections.max(parent).columns);
            //result.add(Collections.max(parent).columns);
            System.out.println(result);

        }
		/*
		for (Integer i : result) {
			System.out.println(i);
		}
		*/

    }

    private static void Union(int d, int s) {
        int dId = Find(d - 1);
        int sId = Find(s - 1);

        if (dId == sId)
            return;

        Table des = parent.get(dId);
        Table sou = parent.get(sId);
        des.columns += sou.columns;
        sou.columns = 0;
        sou.parent = dId;
        parent.set(dId, des);
        parent.set(sId, sou);
        if (result < des.columns)
            result = des.columns;
    }

    private static int Find(int i ) {
        Table temp = parent.get(i);
        if (i != temp.parent) {
            temp.parent = Find(temp.parent);
            parent.set(i, temp);
        }
        return temp.parent;
    }

    private static class Table implements Comparable<Table> {
        int columns;
        int parent;

        Table(int c, int p) {
            this.columns = c;
            this.parent = p;
        }

        @Override
        public int compareTo(Table o) {
            return Integer.compare(this.columns, o.columns);
        }
    }

}

/*
//
import java.util.Scanner;

public class Main {

	private static int find(int[] r, int idx) {
		if (r[idx] < 0) {
			int ret = find(r, -r[idx]);
			r[idx] = -ret;	// path compression
			return ret;
		} else
			return idx;
	}

	public static void main(String[] args) throws Exception {
		try (Scanner in = new Scanner(System.in)) {
			int max = 0;
			int n = in.nextInt();
			int m = in.nextInt();
			int[] r = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				r[i] = in.nextInt();
				if (r[i] > max)
					max = r[i];
			}

			for (int i = 0; i < m; i++) {
				int dest = find(r, in.nextInt());
				int src = find(r, in.nextInt());
				if (dest != src) {
					r[dest] += r[src];
					r[src] = -dest;
					if (r[dest] > max)
						max = r[dest];
				}
				System.out.println(max);
			}
		}
	}

}
 */