package ru.otus.factory.factory;

public class InsertSorter implements Sorter {

    @Override
    public int[] sort(int[] values) {
        int[] result = values.clone();
        for (int i = 1; i < result.length; i++) {
            int current = result[i];
            int j = i - 1;
            while(j >= 0 && current < result[j]) {
                result[j+1] = result[j];
                j--;
            }
            result [j+1] = current;
        }

        return result;
    }
}
