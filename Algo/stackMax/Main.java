package stackMax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
Стек с поддержкой максимума
Реализовать стек с поддержкой операций push, pop и max.
Вход. Последовательность запросов push, pop и max .
Выход. Для каждого запроса max вывести максимальное
число, находящее на стеке.
Sample Input 1:

5
push 2
push 1
max
pop
max

Sample Output 1:

2
2

Sample Input 2:

5
push 1
push 2
max
pop
max

Sample Output 2:

2
1

Sample Input 3:

10
push 2
push 3
push 9
push 7
push 2
max
max
max
pop
max

Sample Output 3:

9
9
9
9
 */
public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stackMax = new Stack<>();

        for (int i = 0; i < count; i++) {
            String[] line = reader.readLine().split(" ");
            String command = line[0];

            switch(command) {
                case "push" :
                    int push = Integer.parseInt(line[1]);
                    stack.push(push);
                    if (stackMax.isEmpty()) {
                        stackMax.push(push);
                    } else {
                        int max = stackMax.peek();
                        stackMax.push(Math.max(max, push));
                    }
                    break;
                case "max" :
                    if (!stackMax.isEmpty()) {
                        System.out.println(stackMax.peek());
                        break;
                    }
                case "pop" :
                    if (!stack.isEmpty()) {
                        stack.pop();
                        stackMax.pop();
                    }
            }
        }
    }

}

