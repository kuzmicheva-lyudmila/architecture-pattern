package ru.otus.kl.iterators;

import java.io.IOException;
import java.math.BigInteger;

public interface Client {

    void getForwardRange(BigInteger minValue, BigInteger maxValue) throws IOException;
    void getReverseRange(BigInteger minValue, BigInteger maxValue) throws IOException;
}
