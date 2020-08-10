package ru.otus.kl;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class MatrixOperations {

    private final int threadCount;

    public Matrix multiply(Matrix a, Matrix b) throws InterruptedException {
        if (a.getDim() != b.getDim()) {
            throw new IllegalArgumentException("Unable to multiply matrix with different dimension");
        }

        Thread[] threads = new MultiplyThread[threadCount];
        Matrix resultMatrix = new Matrix(a.getDim());
        MatrixIterator iterator = new MatrixIterator(resultMatrix);
        Arrays.stream(threads)
                .map(thread -> new MultiplyThread(iterator, a, b))
                .forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }

        return new Matrix(a.getDim());
    }
}
