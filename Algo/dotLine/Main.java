package dotLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
Задача на программирование: точки и отрезки

﻿В первой строке задано два целых числа 1≤n≤50000 и 1≤m≤50000 — количество отрезков и точек на прямой, соответственно.
Следующие n строк содержат по два целых числа ai и bi (ai≤bi) — координаты концов отрезков.
Последняя строка содержит m целых чисел — координаты точек. Все координаты не превышают 10^8 по модулю.
Точка считается принадлежащей отрезку, если она находится внутри него или на границе.
Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.

Sample Input:

2 3
0 5
7 10
1 6 11

Sample Output:

1 0 0
 */
class Main {
    public static void main(String[] args)throws IOException {
        // put your code here
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line1 = reader.readLine().split(" ");
        int countLine = Integer.parseInt(line1[0]);
        int points = Integer.parseInt(line1[1]);

        int[] right = new int[countLine];
        int[] left = new int[countLine];
        for (int i = 0; i < countLine; i++) {
            String[] lines = reader.readLine().split(" ");
            left[i] = Integer.parseInt(lines[0]);
            right[i] = Integer.parseInt(lines[1]);
        }
        Arrays.sort(left);
        Arrays.sort(right);
        String[] strPoints = reader.readLine().split(" ");
        for (int i = 0; i < points; i++) {
            int point = Integer.parseInt(strPoints[i]);
            int indexLeft = 0;
            int indexRight = 0;
            indexLeft = binSearchLeft(left, point);
            //System.out.println("indexLeft = " + indexLeft + "point = " + point);
            indexRight = binSearchRight(right, point);
            //System.out.println("indexRight = " + indexRight + "point = " + point);
            //System.out.println("");
            System.out.print(indexLeft-indexRight + " ");
			/*//Все работает, но долго
			int point = Integer.parseInt(strPoints[i]);
			int indexLeft = 0;
			int indexRight = 0;
			for (int j = 0; j < countLine; j++) {
				if (left[j] <= point)
					indexLeft++;
				if (right[j] < point)
					indexRight++;
			}
			System.out.print(indexLeft-indexRight + " ");
			*/
        }
    }
    private static int binSearchLeft(int[] a, int x) {
        int l = -1;
        int r = a.length;
        while (r > l + 1) {
            int m = (l + r) >> 1;
            if (a[m] <= x) {
                l = m;
            } else {
                r = m;
            }
        }
        if ( l >= 0 && a[l] <= x) {
            return l + 1;
        } else
            return 0;

    }

    private static int binSearchRight(int[] a, int x) {
        int l = -1;
        int r = a.length;
        while (r > l + 1) {
            int m = (l + r) >> 1;
            if (a[m] < x) {
                l = m;
            } else {
                r = m;
            }
        }
        if ( l >= 0 && a[l] < x) {
            return l + 1;
        } else
            return 0;

    }
}
/*
//другой вариант
import java.util.*;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in).useDelimiter(Pattern.compile("\\s"));

        int[] startingPoints = new int[in.nextInt()];
        int[] endPoints = startingPoints.clone();
        int[] points = new int[in.nextInt()];

        for (int i = 0; i < startingPoints.length; i++) {
            startingPoints[i] = in.nextInt();
            endPoints[i] = in.nextInt();
        }

        for (int i = 0; i < points.length; i++) {
            points[i] = in.nextInt();
        }

        Arrays.sort(startingPoints);
        Arrays.sort(endPoints);
        System.out.println(getPrintableCounts(startingPoints, endPoints, points));
    }


    private static int firstOccurrenceBinarySearch(int[] source, int needle) {
        int low = 0;
        int high = source.length - 1;
        int firstOccurrence = Integer.MIN_VALUE;

        while (low <= high) {
            int middle = low + ((high - low) >>> 1);

            if (source[middle] == needle) {
                firstOccurrence = middle;
                high = middle - 1;
            } else if (source[middle] < needle) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        if (firstOccurrence != Integer.MIN_VALUE) {
            return firstOccurrence;
        }

        return -(low + 1);
    }

    static String getPrintableCounts(int[] startingPointsSorted, int[] endPointsSorted, int[] pointsSorted) {

        String prefix = "";
        StringBuilder stringBuilder = new StringBuilder();

        int countIncludes;
        int countExcludes;

        for (int point : pointsSorted) {
            countIncludes = getCountRightPoints(point, endPointsSorted, false);
            countExcludes = getCountRightPoints(point, startingPointsSorted, true);
            stringBuilder.append(prefix).append(countIncludes - countExcludes);
            prefix = " ";
        }
        return stringBuilder.toString();
    }

    private static int getCountRightPoints(int point, int[] sortedPoints, boolean forStart) {
        int res = firstOccurrenceBinarySearch(sortedPoints, point);
        if (res >= 0) {
            res = sortedPoints.length - res;
            if (forStart && res !=0) {
                return getCountRightPoints(point + 1, sortedPoints, false);
            }
            return res;
        }

        res = sortedPoints.length - (-1 - res);

        if (res > sortedPoints.length) {
            return 0;
        }

        return res;
    }
}
 */