package hashBranch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

/*
Хеширование цепочками — один из наи-
более популярных методов реализации
хеш-таблиц на практике. Ваша цель в дан-
ной задаче — реализовать такую схему, ис-
пользуя таблицу с m ячейками и полино-
миальной хеш-функцией на строках
Ваша программа должна поддерживать следующие
типы запросов:
• add string: добавить строку string в таблицу. Если такая
строка уже есть, проигнорировать запрос;
• del string: удалить строку string из таблицы. Если такой
строки нет, проигнорировать запрос;
• find string: вывести «yes» или «no» в зависимости от того,
есть в таблице строка string или нет;
• check i: вывести i-й список (используя пробел в качестве раз-
делителя); если i-й список пуст, вывести пустую строку.
При добавлении строки в цепочку, строка должна добавляться в на-
чало цепочки.
Формат входа. Первая строка размер хеш-таблицы m. Следующая
строка содержит количество запросов n. Каждая из последую-
щих n строк содержит запрос одного из перечисленных выше
четырёх типов.
Формат выхода. Для каждого из запросов типа find и check выве-
дите результат в отдельной строке.
 Sample Input 1:

5
12
add world
add HellO
check 4
find World
find world
del world
check 4
del HellO
add luck
add GooD
check 2
del good

Sample Output 1:

HellO world
no
yes
HellO
GooD luck

Sample Input 2:

4
8
add test
add test
find test
del test
find test
find Test
add Test
find Test

Sample Output 2:

yes
no
no
yes
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(reader.readLine());
        int count = Integer.parseInt(reader.readLine());

        List<String> array[] = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            array[i] = new ArrayList<String>();
        }

        for (int i = 0; i < count; i++) {

            String[] line = reader.readLine().split(" ");
            switch(line[0]) {
                case "add" :
                    int indexAdd = (int)hashFun(line[1], m);
                    List listAdd = array[indexAdd];
                    if (listAdd.isEmpty()) {
                        listAdd.add(0, line[1]);
                    } else if (!listAdd.contains(line[1])) {
                        listAdd.add(0, line[1]);
                    }

                    break;
                case "del" :
                    int indexDel = (int)hashFun(line[1], m);
                    List listDel = array[indexDel];
                    if (!listDel.isEmpty()) {
                        if (listDel.contains(line[1])) {
                            listDel.remove(line[1]);
                        }
                    }
                    break;
                case "find" :
                    int indexFind = (int)hashFun(line[1], m);
                    List listFind = array[indexFind];
                    if (!listFind.isEmpty()) {
                        if (!listFind.contains(line[1])) {
                            System.out.println("no");
                        } else {
                            System.out.println("yes");
                        }
                    } else {
                        System.out.println("no");
                    }
                    break;
                case "check" :
                    List listCheck = array[Integer.parseInt(line[1])];
                    if (listCheck.isEmpty()) {
                        System.out.println(" ");
                    } else {
                        for (int j = 0; j < listCheck.size(); j++) {
                            System.out.print(listCheck.get(j) + " ");
                        }
                    }
                    System.out.println("");
                    break;
            }

        }


    }

    private static int hashFun(String str, int m) {
        char[] ch = str.toCharArray();
        //long result = 0;

        BigInteger result = BigInteger.valueOf(0);
        BigInteger power = BigInteger.valueOf(263);
        BigInteger mod = BigInteger.valueOf(1000000007);
        BigInteger mm = BigInteger.valueOf(m);

        for (int i = 0; i < ch.length; i++) {
            //result = ( ( (((int)ch[i]) % 1000000007) * (((long)Math.pow(263, i) % 1000000007)) % 1000000007) + result) % 1000000007;
            BigInteger chMod = BigInteger.valueOf((int)ch[i]).mod(mod);
            BigInteger powerMod = power.pow(i).mod(mod);
            BigInteger multi = chMod.multiply(powerMod).mod(mod);
            result = result.add(multi).mod(mod);
        }
        result = result.mod(mm);
        return result.intValue();
        //return result % m;
    }
}

/*
//
import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int m = Integer.parseInt(scanner.nextLine());
		int n = Integer.parseInt(scanner.nextLine());

		Map<Integer, List<String>> map = new HashMap<>();
		List<String> result = new ArrayList<>();
		List<String> buff;
		String[] line;
		int element;

		for (int i = 0; i < n; i++) {
			line = scanner.nextLine().split(" ");
			element = hash0(line[1], m);
			buff = map.get(element);

			switch (line[0]) {
				case "add":
					if (map.containsKey(element)) {
						if (buff.contains(line[1])) {
							break;
						} else {
							buff.add(0, line[1]);
						}
					} else {
						List<String> list = new LinkedList<>();
						list.add(0, line[1]);

						map.put(element, list);
					}

					break;
				case "del":
					if (map.containsKey(element)) {
						if (buff.contains(line[1])) {
							buff.remove(line[1]);
						}
					}

					break;
				case "find":
					if (map.containsKey(element)) {
						if (buff.contains(line[1])) {
							result.add("yes");
						} else {
							result.add("no");
							break;
						}
					} else {
						result.add("no");
					}

					break;
				case "check":
					if (map.containsKey(Integer.parseInt(line[1]))) {
						StringBuilder res = new StringBuilder();

						buff = map.get(Integer.parseInt(line[1]));

						for (String s : buff) {
							res.append(s).append(" ");
						}

						result.add(res.toString().trim());

					} else {
						result.add("");
					}

					break;
			}
		}

		for (String str : result) {
			System.out.println(str);
		}
	}

	private static int hash(String str, int m) {
		long hash = 0;
		char[] chars = str.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			hash += chars[i] * Math.pow(263, i);
		}

		hash = hash % 1_000_000_007;
		hash = hash % m;

		return (int) hash;
	}

	private static int hash0(String str, int m) {
		BigInteger hash = new BigInteger("0");
		char[] chars = str.toCharArray();

		BigInteger chi;
		BigInteger powi;

		for (int i = 0; i < chars.length; i++) {
			int ch = (int) chars[i];

			chi = new BigInteger(Integer.toString(ch));
			powi = new BigInteger("263").pow(i);

			hash = hash.add(chi.multiply(powi));
		}

		hash = hash.mod(new BigInteger("1000000007"));
		hash = hash.mod(new BigInteger(Integer.toString(m)));

		return hash.intValue();
	}
}

 */