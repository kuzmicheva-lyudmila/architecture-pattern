package ru.otus.kl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Cell {

    private final int row;

    private final int column;
}
