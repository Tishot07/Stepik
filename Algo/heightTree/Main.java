package heightTree;

import java.io.*;
import java.util.*;
/*
Высота дерева
Вычислить высоту данного дерева.
Вход. Корневое дерево с вершинами {0, . . . , n−1}, заданное
как последовательность parent 0 , . . . , parent n−1 , где parent i —
родитель i-й вершины.
Выход. Высота дерева.
Sample Input:
10
9 7 5 5 2 9 9 9 2 -1
Sample Output:
4
 */
public class Main {
    static int[] k;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());
        String[] array = reader.readLine().split(" ");

        Map<Integer, List<Integer>> tree = new HashMap<>();
        k = new int[count];
        for (int i = 0; i < count; i++) {
            k[i] = -1;
            tree.put(i, new ArrayList<>());
        }


        int root = -1;
        for (int i = 0; i < count; i++) {
            int j = Integer.parseInt(array[i]);			//узел, -1 - это корень
            if (j == -1)
                root = i;

            if (tree.containsKey(j)) {
                List<Integer> list = tree.get(j);
                list.add(i);
                tree.put(j, list);
            }

        }

		/*//checked
		for (Map.Entry<Integer, List<Integer>> map : tree.entrySet()) {
			System.out.println(map.getKey());
			for (Integer list : map.getValue()) {
				System.out.println(map.getKey() + "[" + list + "]");
			}
		}
*/
        for (int i = 0; i < count; i++) {
            Height(tree, i);
        }
        System.out.println(k[root]);

    }

    private static int Height(Map<Integer, List<Integer>> map, int r) {
        int height = 1;
        List<Integer> child = map.get(r);
        if (k[r] != -1) {
            return k[r];
        } else {
            for (int i = 0; i < child.size(); i++) {
                if (k[child.get(i)] == -1)
                    k[child.get(i)] =  Height(map, child.get(i));
                height = Math.max(height, 1 + k[child.get(i)]);
            }
            k[r] = height;
        }

        return height;
    }

}
/*
//Другое решение
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int[] tree =  new int[scanner.nextInt()];
        for (int a = 0; a < tree.length; a++) {
            tree[a] = scanner.nextInt();
        }
        int height = 1;
        for(int a = 0 ; a < tree.length; a++) {
          height = Math.max(height, countHeight(tree,a));
        }
        System.out.println(height);

    }

    public static int countHeight(int[] tree, int index) {
       int height = 1;
        if(tree[index] != -1) {
                height = Math.max(height, 1 + countHeight(tree,tree[index]));
            }
            else return height;
        return height;
}
}
 */
