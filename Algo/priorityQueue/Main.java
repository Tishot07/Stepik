package priorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Задача на программирование: очередь с приоритетами

Первая строка входа содержит число операций 1≤n≤10^5.
Каждая из последующих n строк задают операцию одного из следующих двух типов:
	Insert x, где 0≤x≤10^9 — целое число;
	ExtractMax.
Первая операция добавляет число x в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.

Sample Input:

6
Insert 200
Insert 10
ExtractMax
Insert 5
Insert 500
ExtractMax

Sample Output:

200
500
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());

        Tree tree = new Tree();
        List<Long> answer = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String[] line = reader.readLine().split(" ");
            String command = line[0];
            switch (command) {
                case "Insert":
                    Long num = Long.parseLong(line[1]);
                    tree.insert(num);
                    break;
                case "ExtractMax":

                    answer.add(tree.deleteMax().key);
            }
        }

        for (Long l : answer) {
            System.out.println(l);
        }
    }

    static class Node {
        long key;
        Node left;
        Node right;
    }

    static class Tree {
        Node root;

        void insert(long k) {
            Node newNode = new Node();
            newNode.key = k;

            if (root == null) {
                root = newNode;
            } else {
                Node current = root;
                Node parent;

                while(true) {
                    parent = current;
                    //left
                    if (k > current.key) {
                        current = current.left;
                        if (current == null) {
                            parent.left = newNode;
                            return;
                        }
                    } else {
                        //right
                        current = current.right;
                        if (current == null) {
                            parent.right = newNode;
                            return;
                        }
                    }
                }
            }
        }

        Node deleteMax() {
            Node current = root;
            Node parent = root;
            boolean isMaxTrue = false;
            Node max = root;
            if (root == null) {
                return null;
            }

            while (!isMaxTrue) {
                parent = current;
                current = current.left;
                {
                    if (current != null) {
                        if (current.left == null) {
                            max = current;
                            isMaxTrue = true;
                            break;
                        }
                    } else {
                        max = parent;
                        isMaxTrue = true;
                    }
                }

            }

            //not children
            if (max.left == null && max.right == null) {
                if (max == root) {
                    root = null;
                } else {
                    parent.left = null;
                }
            } else {
                //левых детей нет (иначе это был бы мах), 2 случая - либо нет детей, либо есть правый - он станет на место родителя
                if (max == root) {
                    root = max.right;
                } else {
                    parent.left = max.right;
                }
            }

            return max;
        }
    }
}
/*
//Другое решение
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    protected static List<Integer> queue = new ArrayList<>();
    protected static int queueTail = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String inputLine = scanner.nextLine();
            if (inputLine.equals("ExtractMax")) {
                extractMax();
            } else {
                insert(Integer.parseInt(inputLine.split(" ")[1]));
            }
        }
    }

    protected static int parent (int index) {
        return index / 2;
    }

    protected static int leftSonIndex (int index) {
        return (index * 2);
    }

    protected static int rightSonIndex (int index) {
        return ((index * 2) + 1);
    }

    protected static void shiftUp (int index) {
        int nowItem = queue.get(index - 1);
        while (index > 1 && queue.get(index - 1) > queue.get(parent(index) - 1)) {
            queue.set(index - 1, queue.get(parent(index) - 1));
            queue.set(parent(index) - 1, nowItem);
            index = parent(index);
        }
    }

    protected static void shiftDown (int index) {
        int leftSon = leftSonIndex(index);
        int rightSon = rightSonIndex(index);
        int targetSon = leftSon;
        if (queue.size() <= 1 || leftSon > queue.size()) {
            return;
        }
        if (rightSon <= queue.size() && queue.get(leftSon - 1) < queue.get(rightSon - 1)) {
            targetSon = rightSon;
        }
        if (queue.get(targetSon - 1) <= queue.get(index - 1)) {
            return;
        }
        int nowItem = queue.get(index - 1);
        queue.set(index - 1, queue.get(targetSon - 1));
        queue.set(targetSon - 1, nowItem);
        index = targetSon;
        shiftDown(index);
    }

    protected static void insert (int value) {
        queue.add(value);
        shiftUp(queue.size());
        }

    protected static void extractMax () {
        String max = String.valueOf(queue.get(0));
        queue.set(0, queue.get(queue.size() - 1));
        queue.remove((queue.size() - 1));
        shiftDown(1);
        System.out.println(max);
    }
}
 */
