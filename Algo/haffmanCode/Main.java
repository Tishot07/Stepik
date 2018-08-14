package haffmanCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Задача на программирование: кодирование Хаффмана

По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита, постройте оптимальный беспрефиксный код.
В первой строке выведите количество различных букв k, встречающихся в строке, и размер получившейся закодированной строки.
В следующих k строках запишите коды букв в формате "letter: code". В последней строке выведите закодированную строку.

Sample Input 1:

a

Sample Output 1:

1 1
a: 0
0

Sample Input 2:

abacabad

Sample Output 2:

4 14
a: 0
b: 10
c: 110
d: 111
01001100100111

 */
public class Main {
    public static void main(String[] args) throws IOException {
        // put your code here


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();
        //записываем символы и их частоты
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : str.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        //кидаем листья в приоритетную очередь
        //за одно мапа с сиволом и его кодом
        Map<Character, Node> mapNodes = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> t : map.entrySet()) {
            Leaf leaf = new Leaf(t.getKey(), t.getValue());
            mapNodes.put(t.getKey(), leaf);
            queue.add(leaf);
        }
        //длинна кода
        int summ = 0;
        //пока в очереди больше 1 элемента - строим узлы дерева, считая количество кодов при каждом проходе
        while (queue.size() > 1) {
            Node first = queue.poll();
            Node second = queue.poll();
            Node n = new NodeWithChildren(first, second);
            summ += n.count;
            queue.add(n);
        }
        //если только один символ
        if (map.size() == 1) {
            summ = str.length();
        }
        //выводим количество символов в строке и длинну кодовой строки
        System.out.println(map.size() + " " + summ);
        //корень
        Node root = queue.poll();
        //запускаем построение кодовой строки (для одного символа код 0)
        if (map.size() == 1) {
            root.AddCode("0");
        } else {
            root.AddCode("");
        }

        //сопоставляем каждой букве ее код
        StringBuffer buffer = new StringBuffer();
        for (Character ch : str.toCharArray()) {
            buffer.append(mapNodes.get(ch).code);
        }
        System.out.println(buffer.toString());

    }

    static class Node implements Comparable<Node> {
        int count;
        String code;

        Node (int c) {
            count = c;
        }

        @Override
        public int compareTo(Node o) {
            // TODO Auto-generated method stub
            return Integer.compare(count, o.count);
        }

        void AddCode(String c) {
            code = c;
        }

    }

    static class NodeWithChildren extends Node {
        Node left;
        Node right;

        NodeWithChildren(Node l, Node r) {
            super(l.count + r.count);
            left = l;
            right = r;
        }

        void AddCode(String c) {
            super.AddCode(c);
            left.AddCode(c + "0");
            right.AddCode(c + "1");
        }
    }

    static class Leaf extends Node {
        char symbol;

        Leaf(char s, int c) {
            super(c);
            // TODO Auto-generated constructor stub
            symbol = s;
        }

        void AddCode(String c) {
            super.AddCode(c);
            System.out.println(symbol + ": " + c);
        }
    }
}
