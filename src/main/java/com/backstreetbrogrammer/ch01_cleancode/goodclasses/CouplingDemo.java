package com.backstreetbrogrammer.ch01_cleancode.goodclasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CouplingDemo {

    // Not programming to an Interface
    LinkedList<String> list = new LinkedList<>();

    void doSomething(final LinkedList<String> list) {
        final String firstElem = list.getFirst();

        // do something with firstElem
    }

    void doSomethingElse(final LinkedList<String> list) {
        final String lastElem = list.getLast();

        // do something with lastElem
    }


    // Programming to an Interface
    List<String> list2 = new ArrayList<>();

    void doSomething2(final List<String> list) {
        final String firstElem = list.get(0);

        // do something with firstElem
    }

    void doSomethingElse2(final List<String> list) {
        final String lastElem = list.get(list.size() - 1);

        // do something with lastElem
    }

}
