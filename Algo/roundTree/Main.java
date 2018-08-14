package roundTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Обход двоичного дерева
Построить in-order, pre-order и post-order обходы данного двоичного де-
рева.
Вход. Двоичное дерево.
Выход. Все его вершины в трёх разных порядках: in-order,
pre-order и post-order.
Формат входа. Первая строка содержит число вершин n. Вершины
дерева пронумерованы числами от 0 до n−1. Вершина 0 является
корнем. Каждая из следующих n строк содержит информацию о
вершинах 0, 1, . . . , n − 1: i-я строка задаёт числа key i , left i и right i ,
где key i — ключ вершины i, left i — индекс левого сына верши-
ны i, а right i — индекс правого сына вершины i. Если у верши-
ны i нет одного или обоих сыновей, соответствующее значение
равно −1.
Формат выхода. Три строки: in-order, pre-order и post-order обходы.
 Sample Input:

10
0 7 2
10 -1 -1
20 -1 6
30 8 9
40 3 -1
50 -1 -1
60 1 -1
70 5 4
80 -1 -1
90 -1 -1

Sample Output:

50 70 80 30 90 40 0 20 10 60
0 70 50 40 30 80 90 20 60 10
50 80 90 30 40 70 10 60 20 0
 */
public class Main {

    private static String[] array;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int x = Integer.parseInt(reader.readLine());



        array = new String[x];
        for (int i = 0; i < x; i++) {
            array[i] = reader.readLine();
        }

        Tree root = create(null, 0);
        root.inOrder(root);
        System.out.println("");
        root.preOrder(root);
        System.out.println("");
        root.postOrder(root);


    }

    private static Tree create(Tree root, int i) {
        String[] temp = array[i].split(" ");
        int key = Integer.parseInt(temp[0]);
        int left = Integer.parseInt(temp[1]);
        int right = Integer.parseInt(temp[2]);
        Tree newTree = new Tree(key);
        newTree.parent = root;
        if (left != -1) {
            newTree.left = create(newTree, left);
        }
        if (right != -1) {
            newTree.right = create(newTree, right);
        }
        return newTree;
    }

    private static class Tree {
        int key;
        Tree parent;
        Tree left;
        Tree right;

        Tree(int k) {
            this.key = k;
        }

        void inOrder(Tree root) {
            if (root != null) {
                inOrder(root.left);
                System.out.print(root.key + " ");
                inOrder(root.right);
            }
        }

        void preOrder(Tree root) {
            if (root != null) {
                System.out.print(root.key + " ");
                preOrder(root.left);
                preOrder(root.right);
            }
        }

        void postOrder(Tree root) {
            if (root != null) {
                postOrder(root.left);
                postOrder(root.right);
                System.out.print(root.key + " ");
            }
        }


    }

}

