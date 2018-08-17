package checkedBinTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
Проверка свойства дерева поиска
Проверить, является ли данное двоичное дерево деревом поиска.
Вход. Двоичное дерево.
Выход. Проверить, является ли оно корректным деревом
поиска: верно ли, что для любой вершины дерева её ключ
больше всех ключей в левом поддереве данной вершины и
меньше всех ключей в правом поддереве.
Вы тестируете реализацию двоичного дерева поиска. У вас уже на-
писан код, который ищет, добавляет и удаляет ключи, а также выво-
дит внутреннее состояние структуры данных после каждой операции.
Вам осталось проверить, что в каждый момент дерево остаётся кор-
ректным деревом поиска. Другими словами, вы хотите проверить,
что для дерева корректно работает поиск, если ключ есть в дереве,
то процедура поиска его обязательно найдёт, если ключа нет — то не
найдёт.
Формат входа. Первая строка содержит число вершин n. Вершины
дерева пронумерованы числами от 0 до n−1. Вершина 0 является
корнем. Каждая из следующих n строк содержит информацию о
вершинах 0, 1, . . . , n − 1: i-я строка задаёт числа key i , left i и right i ,
где key i — ключ вершины i, left i — индекс левого сына верши-
ны i, а right i — индекс правого сына вершины i. Если у верши-
ны i нет одного или обоих сыновей, соответствующее значение
равно −1.
Формат выхода. Выведите «CORRECT», если дерево является кор-
ректным деревом поиска, и «INCORRECT» в противном случае.
 Sample Input:

3
2 1 2
1 -1 -1
3 -1 -1

Sample Output:

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


    private static boolean check(Tree n, int min, int max) {

        if (n.key <= min || n.key > max)
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

        if(check(node[0], Integer.MIN_VALUE, Integer.MAX_VALUE))
            System.out.println("CORRECT");
        else
            System.out.println("INCORRECT");

    }

}
