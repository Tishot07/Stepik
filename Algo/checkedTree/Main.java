package checkedTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
Данная задача полностью аналогична предыдущей, но проверять те-
перь нужно более общее свойство. Дереву разрешается содержать
равные ключи, но они всегда должны находиться в правом поддереве.
Формально, двоичное дерево называется деревом поиска, если для
любой вершины её ключ больше всех ключей из её левого поддерева
и не меньше всех ключей из правого поддерева.
Ограничения. 0 ≤ n ≤ 10 5 ; −2 31 ≤ key i ≤ 2 31 −1 (таким образом, в ка-
честве ключей допустимы минимальное и максимальное зна-
чение 32-битного целого типа, будьте осторожны с переполне-
нием); −1 ≤ left i , right i ≤ n − 1. Гарантируется, что вход зада-
ёт корректное двоичное дерево: в частности, если left i 6 = −1 и
right i 6 = −1, то left i 6 = right i ; никакая вершина не является сыном
двух вершин; каждая вершина является потомком корня.
Пример.
Вход:
3
2 1 2
1 -1 -1
3 -1 -1
Выход:
CORRECT
 */
public class Main {

    private static Tree[] node;

    private static class Tree {
        int key;
        int left;
        int right;

        Tree(int k, int left, int right) {
            this.key = k;
            this.left = left;
            this.right = right;
        }

    }

    private static boolean check(Tree n, long min, long max) {

        if (n.key < min || n.key >= max)
            return false;
        boolean left = true;
        boolean right = true;
        if (n.left >= 0)
            left = check(node[n.left], min, n.key);
        if (n.right >= 0)
            right = check(node[n.right], n.key, max);
        return  left && right;
    }


    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());

        if (count == 0) {
            System.out.println("CORRECT");
            return;
        }

        node = new Tree[count];
        for (int i = 0; i < count; i++) {
            String[] temp = reader.readLine().split(" ");
            int key = Integer.parseInt(temp[0]);
            int left = Integer.parseInt(temp[1]);
            int right = Integer.parseInt(temp[2]);
            node[i] = new Tree(key, left, right);
        }

        if(check(node[0], Long.MIN_VALUE, Long.MAX_VALUE))
            System.out.println("CORRECT");
        else
            System.out.println("INCORRECT");

    }

}
