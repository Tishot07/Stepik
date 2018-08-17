package backpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Задача на программирование: непрерывный рюкзак
Первая строка содержит количество предметов 1≤n≤10^3 и вместимость рюкзака 0≤W≤2⋅106.
Каждая из следующих n строк задаёт стоимость 0≤ci≤2⋅106 и объём 0<wi≤2⋅106 предмета (n, W, ci, wi — целые числа).
Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть,
стоимость и объём при этом пропорционально уменьшатся), помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.

Sample Input:

3 50
60 20
100 50
120 30

Sample Output:

180.000
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] str = reader.readLine().split(" ");
        double countObject = Double.parseDouble(str[0]);
        double valuePack = Double.parseDouble(str[1]);

        List list = new ArrayList<Objects>();

        for (int i = 0; i < countObject; i++) {
            String[] temp = reader.readLine().split(" ");

            list.add(new Objects(Double.parseDouble(temp[0]), Double.parseDouble(temp[1])));
        }

        Collections.sort(list, new Comparator<Objects>() {
            @Override
            public int compare(Objects o1, Objects o2) {
                return o1.getWeight() > o2.getWeight() ? +1 : o1.getWeight() < o2.getWeight() ? -1 : 0;
            }
        });


        double summ = 0;
        for (int i = list.size() -1; i >= 0 ; i--) {
            Objects temp = (Objects) list.get(i);
            double summTemp = temp.getWeight();
            while (valuePack > 0 && temp.weight > 0) {
                summ += summTemp;
                valuePack--; temp.weight--;

            }

        }
        System.out.printf("%.3f", summ);

    }

    private static class Objects {
        double cast;
        double weight;

        Objects (double c, double w) {
            cast = c;
            weight = w;
        }

        double getWeight() {
            return cast/weight;
        }
        @Override
        public String toString() {
            return "(" + cast + "," + weight + ")";
        }

    }
}
