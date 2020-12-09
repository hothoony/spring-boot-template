package study.springbootbasic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static study.springbootbasic.utils.Util.*;

public class UtilTest {

    @Test
    void randomNumber() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(randomBetween(1, 10));
        }
    }

    @Test
    void shuffleTest() {
        Integer[] list = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.asList(list));
        shuffle(list);
        System.out.println(Arrays.asList(list));
    }
}
