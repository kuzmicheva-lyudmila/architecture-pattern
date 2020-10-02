package ru.otus.factory.factory;

public class SortingAction {

    private SortingAction() {
    }

    public static Sorter getSorter(String type) {
        if ("choice".equals(type)) {
            return new ChoiceSorter();
        } else if ("insert".equals(type)) {
            return new InsertSorter();
        } else if ("merge".equals(type)) {
            return new MergeSorter();
        }

        return null;
    }
}
