package com.backstreetbrogrammer.ch01_cleancode;

import org.junit.jupiter.api.Test;

public class CleanCodeDemoTest {

    private final CleanCodeDemo demo = new CleanCodeDemo();

    @Test
    void testBadCode() {
        demo.badCode();
    }

    @Test
    void testCleanCode() {
        demo.cleanCode();
    }
}
