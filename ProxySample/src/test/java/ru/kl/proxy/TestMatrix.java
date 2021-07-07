package ru.kl.proxy;

import org.junit.jupiter.api.Test;
import ru.kl.proxy.proxy.Matrix;
import ru.kl.proxy.proxy.PlusMatrix;
import ru.kl.proxy.proxy.SimpleMatrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestMatrix {

    @Test
    void testOperations() {
        int[][] valueForMatrixA = {
                new int[]{1, 2, 3},
                new int[]{2, 5, 6},
                new int[]{4, 2, 3}
        };

        int[][] valueForMatrixB = {
                new int[]{1, 0, 0},
                new int[]{2, 2, 1},
                new int[]{4, 2, 3}
        };

        Matrix a = new SimpleMatrix(valueForMatrixA);
        Matrix b = new SimpleMatrix(valueForMatrixB);
        int result = new PlusMatrix(a, b).get(1, 1);

        //a.plus(new SimpleMatrix(valueForMatrixB)).mult(new SimpleMatrix(valueForMatrixB), 3).get(1, 1);

        assertEquals(28, result);
    }
}
