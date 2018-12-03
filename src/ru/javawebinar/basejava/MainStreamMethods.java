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
        int[] ints = IntStream.of(values).distinct().toArray();
        int length = ints.length;
        int count = (int) Math.pow(10, length - 1);
        int requiredNumber = 0;
        for (int i : ints) {
            requiredNumber += i * count;
            count /= 10;
        }
        return requiredNumber;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        Map<Boolean, List<Integer>> collect = integers.stream().collect(Collectors.partitioningBy(number -> number % 2 == 0));
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        if (sum % 2 == 0) {
            return collect.get(false);
        } else {
            return collect.get(true);
        }
    }
}
