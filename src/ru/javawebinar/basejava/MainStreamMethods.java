package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainStreamMethods {
    public static void main(String[] args) {
        int[] values = {1, 2, 3, 3, 2, 3};
        List<Integer> integers = Arrays.asList(1, 2, 3, 3, 2, 3);

        minValue(values);
        oddOrEven(integers);
    }

    private static int minValue(int[] values) {
        return IntStream.of(values).distinct().reduce((left, right) -> left * 10 + right).getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> collect = integers.stream().collect(Collectors.partitioningBy(number -> number % 2 == 0));
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        return collect.get(sum % 2 == 0);
    }
}
