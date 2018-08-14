package checkedBrackets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
Скобки в коде
Проверить, правильно ли расставлены скобки в данном коде.
Вход. Исходный код программы.
Выход. Проверить, верно ли расставлены скобки. Если нет,
выдать индекс первой ошибки.
Пример.
Вход:
{[]}()
Выход:
Success
Вход:
{[}
Выход:
3
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String str = reader.readLine();

        Stack<Character> stack = new Stack<>();
        Stack<Integer> error = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char item = str.charAt(i);
            if (item == '(' || item == '{' || item == '[' ) {
                stack.push(item);
                int index = i;
                error.push(++index);
            } else if (item == '}' || item == ']' || item == ')') {
                if (stack.isEmpty()) {
                    System.out.println(i + 1 );
                    return;
                }
                char itemBack = stack.pop();
                if (itemBack == '(' && item != ')' || itemBack == '[' && item != ']' || itemBack == '{' && item != '}') {
                    System.out.println(i + 1);
                    return;
                } else {
                    error.pop();
                }
            }
        }
        if (!error.isEmpty()) {
            System.out.println(error.pop());
        } else {
            System.out.println("Success");
        }
    }
}
//else if (item != '(' || item != '{' || item != '[' || item != '}' || item != ']' || item != ')')
