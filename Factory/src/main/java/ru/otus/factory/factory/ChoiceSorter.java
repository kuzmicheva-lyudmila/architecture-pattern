package ru.otus.factory.factory;

public class ChoiceSorter implements Sorter {

    @Override
    public int[] sort(int[] values) {
        int[] result = values.clone();
        for (int i = 0; i < result.length; i++) {
            int min = result[i];
            int minId = i;
            for (int j = i+1; j < result.length; j++) {
                if (result[j] < min) {
                    min = result[j];
                    minId = j;
                }
            }
            int temp = result[i];
            result[i] = min;
            result[minId] = temp;
        }

        return result;
    }
}
