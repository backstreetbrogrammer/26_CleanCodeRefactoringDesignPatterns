package com.backstreetbrogrammer.ch01_cleancode.goodclasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CouplingDemo {

    // Not programming to an Interface
    LinkedList<String> list = new LinkedList<>();

    void doSomething(LinkedList<String> list) {
        String firstElem = list.getFirst();

        // do something with firstElem
    }

    void doSomethingElse(LinkedList<String> list) {
        String lastElem = list.getLast();

        // do something with lastElem
    }


    // Programming to an Interface
    List<String> list2 = new ArrayList<>();

    void doSomething2(List<String> list) {
        String firstElem = list.get(0);

        // do something with firstElem
    }

    void doSomethingElse2(List<String> list) {
        String lastElem = list.get(list.size() - 1);

        // do something with lastElem
    }

}
