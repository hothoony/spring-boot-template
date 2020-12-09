package study.springbootbasic.utils;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static int randomBetween(int start, int end) {
        return (int) ((Math.random() * (end - start + 1)) + start);
    }

    public static void shuffle(Integer[] list) {
        for (int i = 0; i < list.length; i++) {
            int x = randomBetween(0, list.length - 1);
            int y = randomBetween(0, list.length - 1);
            int temp = list[x];
            list[x] = list[y];
            list[y] = temp;
        }
    }

    public static Integer[] genArray(int len) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= len; i++) {
            list.add(i);
        }
        return list.toArray(new Integer[]{});
    }

    public static Integer[] createShuffledArray(int len) {
        Integer[] ints = genArray(len);
        shuffle(ints);
        return ints;
    }
}
